package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Cart;
import com.newland.mall.entity.Coupon;
import com.newland.mall.entity.CouponUser;
import com.newland.mall.entity.GrouponRules;
import com.newland.mall.enums.CouponStatusEnum;
import com.newland.mall.enums.CouponTimeTypeEnum;
import com.newland.mall.enums.CouponTypeEnum;
import com.newland.mall.exception.BusinessException;
import com.newland.mall.mapper.CartMapper;
import com.newland.mall.mapper.CouponMapper;
import com.newland.mall.mapper.CouponUserMapper;
import com.newland.mall.model.vo.wx.CouponVO;
import com.newland.mall.service.CartService;
import com.newland.mall.service.CouponUserService;
import com.newland.mall.service.CouponVerifyService;
import com.newland.mall.service.GrouponRulesService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 优惠券用户使用表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class CouponUserServiceImpl extends ServiceImpl<CouponUserMapper, CouponUser> implements CouponUserService {
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private GrouponRulesService grouponRulesService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private CouponVerifyService couponVerifyService;

    @Override
    public PageInfo<CouponUser> listCouponUser(Long userId, Long couponId, Integer status, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listCouponUsers(userId, couponId, status));
    }

    @Override
    public PageInfo<CouponVO> listUserCoupon(Long userId, Integer status, PageEntity pageEntity) {
        PageInfo<CouponUser> pageInfo = this.listCouponUser(userId, null, status, pageEntity);
        List<CouponVO> couponVoList = convertCoupon(pageInfo.getList());
        return PageWrapper.newPageInfo(pageInfo, couponVoList);
    }

    @Override
    public List<CouponUser> listByOrderId(Long orderId) {
        return baseMapper.listByOrderId(orderId);
    }

    @Override
    public void assignForRegister(Long userId) {
        List<Coupon> couponList = couponMapper.listByTypeAndStatus(CouponTypeEnum.REGISTER.getKey(), CouponStatusEnum.NORMAL.getKey());
        for (Coupon coupon : couponList) {
            Long couponId = coupon.getId();
            Long count = baseMapper.countByUserIdAndCouponId(userId, couponId);
            if (count > 0) {
                continue;
            }
            Integer limit = coupon.getLimitCount();
            while (limit > 0) {
                CouponUser couponUser = new CouponUser();
                couponUser.setCouponId(couponId);
                couponUser.setUserId(userId);
                Integer timeType = coupon.getTimeType();
                if (CouponTimeTypeEnum.TIME_TYPE_TIME.getKey().equals(timeType)) {
                    couponUser.setStartTime(coupon.getStartTime());
                    couponUser.setEndTime(coupon.getEndTime());
                } else {
                    LocalDateTime now = LocalDateTime.now();
                    couponUser.setStartTime(now);
                    couponUser.setEndTime(now.plusDays(coupon.getDays()));
                }
                baseMapper.insertSelective(couponUser);
                limit--;
            }
        }
    }

    @Override
    public List<CouponUser> listByUserId(Long userId) {
        return baseMapper.listByUserId(userId);
    }

    @Override
    public List<CouponVO> selectList(Long userId, Long cartId, Long grouponRulesId) {
        // 团购优惠
        BigDecimal grouponPrice = BigDecimal.valueOf(0.00);
        GrouponRules grouponRules = grouponRulesService.getById(grouponRulesId);
        if (grouponRules != null) {
            grouponPrice = grouponRules.getDiscount();
        }
        // 商品价格
        List<Cart> checkedGoodsList;
        if (cartId == null || cartId.equals(0)) {
            checkedGoodsList = cartMapper.listByUserId(userId);
        } else {
            Cart cart = cartService.getById(cartId);
            AssertUtil.notNull(cart == null, "购物车商品不存在");
            AssertUtil.isTrue(cart.getUserId().equals(userId), "操作异常");
            checkedGoodsList = new ArrayList<>(1);
            checkedGoodsList.add(cart);
        }
        BigDecimal checkedGoodsPrice = BigDecimal.valueOf(0.00);
        for (Cart cart : checkedGoodsList) {
            //  只有当团购规格商品ID符合才进行团购优惠
            if (grouponRules != null && grouponRules.getGoodsId().equals(cart.getGoodsId())) {
                checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().subtract(grouponPrice).multiply(new BigDecimal(cart.getNumber())));
            } else {
                checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            }
        }
        // 计算优惠券可用情况
        List<CouponUser> couponUserList = baseMapper.listByUserId(userId);
        List<CouponVO> couponVoList = convertCoupon(couponUserList);
        for (CouponVO cv : couponVoList) {
            Coupon coupon = couponVerifyService.checkCoupon(userId, cv.getCid(), cv.getId(), checkedGoodsPrice, checkedGoodsList);
            cv.setAvailable(coupon != null);
        }
        return couponVoList;
    }

    @Override
    public void receive(Long userId, Long couponId) {
        Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
        AssertUtil.notNull(coupon, "优惠卷不存在");

        // 当前已领取数量和总数量比较
        Integer total = coupon.getTotal();
        Long totalCoupons = baseMapper.countByCouponId(couponId);
        if ((total != 0) && (totalCoupons >= total)) {
            throw new BusinessException("优惠券已领完");
        }

        // 当前用户已领取数量和用户限领数量比较
        Integer limit = coupon.getLimitCount();
        Long userCounpons = baseMapper.countByUserIdAndCouponId(userId, couponId);
        if ((limit != 0) && (userCounpons >= limit)) {
            throw new BusinessException("优惠券已经领取过");
        }

        // 优惠券分发类型
        // 例如注册赠券类型的优惠券不能领取
        Integer type = coupon.getType();
        if (type.equals(CouponTypeEnum.REGISTER.getKey())) {
            throw new BusinessException("新用户优惠券自动发送");
        } else if (type.equals(CouponTypeEnum.CODE.getKey())) {
            throw new BusinessException("优惠券只能兑换");
        } else if (!type.equals(CouponTypeEnum.GENERAL.getKey())) {
            throw new BusinessException("优惠券类型不支持");
        }

        // 优惠券状态，已下架或者过期不能领取
        Integer status = coupon.getStatus();
        if (status.equals(CouponStatusEnum.SOLD_OUT.getKey())) {
            throw new BusinessException("优惠券已领完");
        } else if (status.equals(CouponStatusEnum.EXPIRE.getKey())) {
            throw new BusinessException("优惠券已经过期");
        }

        // 用户领券记录
        CouponUser couponUser = new CouponUser();
        couponUser.setCouponId(couponId);
        couponUser.setUserId(userId);
        Integer timeType = coupon.getTimeType();
        if (timeType.equals(CouponTimeTypeEnum.TIME_TYPE_TIME.getKey())) {
            couponUser.setStartTime(coupon.getStartTime());
            couponUser.setEndTime(coupon.getEndTime());
        } else {
            LocalDateTime now = LocalDateTime.now();
            couponUser.setStartTime(now);
            couponUser.setEndTime(now.plusDays(coupon.getDays()));
        }
        baseMapper.insert(couponUser);
    }

    @Override
    public void exchange(Long userId, String code) {
        Coupon coupon = couponMapper.getByCode(code);
        AssertUtil.notNull(coupon, "优惠券不正确");
        Long couponId = coupon.getId();

        // 当前已领取数量和总数量比较
        Integer total = coupon.getTotal();
        Long totalCoupons = baseMapper.countByCouponId(couponId);
        if ((total != 0) && (totalCoupons >= total)) {
            throw new BusinessException("优惠券已兑换");
        }

        // 当前用户已领取数量和用户限领数量比较
        Integer limit = coupon.getLimitCount();
        Long userCounpons = baseMapper.countByUserIdAndCouponId(userId, couponId);
        if ((limit != 0) && (userCounpons >= limit)) {
            throw new BusinessException("优惠券已兑换");
        }

        // 优惠券分发类型
        // 例如注册赠券类型的优惠券不能领取
        Integer type = coupon.getType();
        if (type.equals(CouponTypeEnum.REGISTER.getKey())) {
            throw new BusinessException("新用户优惠券自动发送");
        } else if (type.equals(CouponTypeEnum.GENERAL.getKey())) {
            throw new BusinessException("优惠券只能领取，不能兑换");
        } else if (!type.equals(CouponTypeEnum.CODE.getKey())) {
            throw new BusinessException("优惠券类型不支持");
        }

        // 优惠券状态，已下架或者过期不能领取
        Integer status = coupon.getStatus();
        if (status.equals(CouponStatusEnum.SOLD_OUT.getKey())) {
            throw new BusinessException("优惠券已领完");
        } else if (status.equals(CouponStatusEnum.EXPIRE.getKey())) {
            throw new BusinessException("优惠券已经过期");
        }

        // 用户领券记录
        CouponUser couponUser = new CouponUser();
        couponUser.setCouponId(couponId);
        couponUser.setUserId(userId);
        Integer timeType = coupon.getTimeType();
        if (timeType.equals(CouponTimeTypeEnum.TIME_TYPE_TIME.getKey())) {
            couponUser.setStartTime(coupon.getStartTime());
            couponUser.setEndTime(coupon.getEndTime());
        } else {
            LocalDateTime now = LocalDateTime.now();
            couponUser.setStartTime(now);
            couponUser.setEndTime(now.plusDays(coupon.getDays()));
        }
        baseMapper.insert(couponUser);
    }

    /**
     * 转换购物卷
     *
     * @return
     */
    private List<CouponVO> convertCoupon(List<CouponUser> couponUsers) {
        List<CouponVO> couponVoList = new ArrayList<>();
        for (CouponUser couponUser : couponUsers) {
            Long couponId = couponUser.getCouponId();
            Coupon coupon = couponMapper.selectByPrimaryKey(couponId);
            CouponVO couponVo = new CouponVO();
            couponVo.setId(couponUser.getId());
            couponVo.setCid(coupon.getId());
            couponVo.setName(coupon.getName());
            couponVo.setDescription(coupon.getDescription());
            couponVo.setTag(coupon.getTag());
            couponVo.setMin(coupon.getMin());
            couponVo.setDiscount(coupon.getDiscount());
            couponVo.setStartTime(couponUser.getStartTime());
            couponVo.setEndTime(couponUser.getEndTime());
            couponVoList.add(couponVo);
        }
        return couponVoList;
    }
}