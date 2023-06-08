package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Comment;
import com.newland.mall.entity.Coupon;
import com.newland.mall.enums.CouponTypeEnum;
import com.newland.mall.mapper.CouponMapper;
import com.newland.mall.service.CouponService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Random;

/**
 * 优惠券信息及规则表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService {
    @Override
    public PageInfo<Coupon> listCoupon(String name, Integer type, Integer status, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listCoupon(name, type, status));
    }

    @Override
    public PageInfo<Coupon> listCoupon(PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listCoupon(null, null, null));
    }

    @Override
    public void add(Coupon coupon) {
        validate(coupon);
        // 如果是兑换码类型，则这里需要生存一个兑换码
        if (coupon.getType().equals(CouponTypeEnum.CODE.getKey())) {
            String code = generateCode();
            coupon.setCode(code);
        }
        baseMapper.insert(coupon);
    }

    @Override
    public Coupon get(Long id) {
        Coupon dbCoupon = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(dbCoupon, "优惠卷不存在");
        return dbCoupon;
    }

    @Override
    public void update(Coupon coupon) {
        validate(coupon);
        Coupon dbCoupon = baseMapper.selectByPrimaryKey(coupon.getId());
        AssertUtil.notNull(dbCoupon, "优惠卷不存在");
        baseMapper.updateByPrimaryKeySelective(coupon);
    }

    @Override
    public void delete(Long id) {
        Coupon dbCoupon = new Coupon();
        dbCoupon.setId(id);
        dbCoupon.setDeleted(1);
        AssertUtil.isTrue(baseMapper.updateByPrimaryKeySelective(dbCoupon) > 0, "删除失败");
    }

    @Override
    public List<Coupon> listAvailableCoupons(Long userId, int pageSize) {
        List<Coupon> list = PageWrapper.list(1, pageSize, () -> baseMapper.listAvailableCoupons(userId));
        return list;
    }

    @Override
    public List<Coupon> listCoupons(int pageSize) {
        List<Coupon> list = PageWrapper.list(1, pageSize, () -> baseMapper.listCoupons());
        return list;
    }

    private void validate(Coupon coupon) {
        String name = coupon.getName();
        AssertUtil.isNotTrue(StringUtils.isEmpty(name), "优惠卷名称不能为空");
    }

    public boolean checkCode(String code) {
        long count = baseMapper.countByCodeAndStatus(code, CouponTypeEnum.CODE.getKey());
        return count == 0;
    }

    /**
     * 生成优惠码
     *
     * @return 可使用优惠码
     */
    public String generateCode() {
        String code = getRandomNum(8);
        while (checkCode(code)) {
            code = getRandomNum(8);
        }
        return code;
    }

    /**
     * 生成随机
     *
     * @param num
     * @return
     */
    private String getRandomNum(Integer num) {
        String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        base += "0123456789";

        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}