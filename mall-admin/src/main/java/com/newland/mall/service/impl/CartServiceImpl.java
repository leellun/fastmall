package com.newland.mall.service.impl;

import com.newland.mall.config.ConfigHelper;
import com.newland.mall.entity.*;
import com.newland.mall.enumeration.BasicEnum;
import com.newland.mall.exception.BusinessException;
import com.newland.mall.mapper.*;
import com.newland.mall.model.dto.wx.CartDTO;
import com.newland.mall.model.vo.wx.CartCheckoutVO;
import com.newland.mall.service.*;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车商品表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Slf4j
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsProductService goodsProductService;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private GrouponRulesMapper grouponRulesMapper;
    @Autowired
    private CouponUserMapper couponUserMapper;
    @Autowired
    private CouponVerifyService couponVerifyService;
    @Autowired
    private ConfigHelper configHelper;

    @Override
    public void updateProduct(Long productId, String goodsSn, String goodsName, BigDecimal price, String url) {
        Cart cart = new Cart();
        cart.setPrice(price);
        cart.setPicUrl(url);
        cart.setGoodsSn(goodsSn);
        cart.setGoodsName(goodsName);
        cart.setProductId(productId);
        baseMapper.updateByProductId(cart);
    }

    @Override
    public List<Cart> listUserCarts(Long userId) {
        List<Cart> list = baseMapper.listByUserId(userId);
        List<Cart> cartList = new ArrayList<>();
        // TODO
        // 如果系统检查商品已删除或已下架，则系统自动删除。
        // 更好的效果应该是告知用户商品失效，允许用户点击按钮来清除失效商品。
        for (Cart cart : list) {
            Goods goods = goodsMapper.selectByPrimaryKey(cart.getGoodsId());
            if (goods == null || !BasicEnum.YES.getKey().equals(goods.getIsOnSale())) {
                baseMapper.deleteLogicalByPrimaryKey(cart.getId());
                log.debug("系统自动删除失效购物车商品 goodsId=" + cart.getGoodsId() + " productId=" + cart.getProductId());
            } else {
                cartList.add(cart);
            }
        }
        return cartList;
    }

    @Override
    public void addCart(Long userId, CartDTO cartDTO) {
        Long productId = cartDTO.getProductId();
        Integer number = cartDTO.getNumber();
        Long goodsId = cartDTO.getGoodsId();

        //判断商品是否可以购买
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        AssertUtil.isNotTrue(goods == null || BasicEnum.NO.getKey().equals(goods.getIsOnSale()), "商品已下架");

        GoodsProduct product = goodsProductService.getById(productId);
        AssertUtil.notNull(product, "货品不存在");
        //判断购物车中是否存在此规格商品
        Cart existCart = baseMapper.getByGoodsIdAndProductIdAndUserId(goodsId, productId, userId);
        if (existCart == null) {
            //取得规格的信息,判断规格库存
            AssertUtil.isNotTrue(number > product.getNumber(), "库存不足");
            Cart cart = new Cart();
            BeanUtils.copyProperties(cartDTO, cart);
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setGoodsName((goods.getName()));
            if (StringUtils.isEmpty(product.getUrl())) {
                cart.setPicUrl(goods.getPicUrl());
            } else {
                cart.setPicUrl(product.getUrl());
            }
            cart.setPrice(product.getPrice());
            cart.setSpecifications(product.getSpecifications());
            cart.setUserId(userId);
            cart.setChecked(BasicEnum.YES.getKey());
            baseMapper.insertSelective(cart);
        } else {
            //取得规格的信息,判断规格库存
            int num = existCart.getNumber() + number;
            AssertUtil.isNotTrue(num > product.getNumber(), "库存不足");
            existCart.setNumber(num);
            if (baseMapper.updateByPrimaryKeySelective(existCart) == 0) {
                throw new BusinessException("添加失败");
            }
        }
    }

    @Override
    public Long fastAddCart(Long userId, CartDTO cartDTO) {
        Long productId = cartDTO.getProductId();
        Integer number = cartDTO.getNumber();
        Long goodsId = cartDTO.getGoodsId();

        //判断商品是否可以购买
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        AssertUtil.isNotTrue(goods == null || BasicEnum.NO.getKey().equals(goods.getIsOnSale()), "商品已下架");

        GoodsProduct product = goodsProductService.getById(productId);
        AssertUtil.notNull(product, "货品不存在");

        //判断购物车中是否存在此规格商品
        Cart existCart = baseMapper.getByGoodsIdAndProductIdAndUserId(goodsId, productId, userId);
        if (existCart == null) {
            //取得规格的信息,判断规格库存
            AssertUtil.isNotTrue(number > product.getNumber(), "库存不足");
            Cart cart = new Cart();
            BeanUtils.copyProperties(cartDTO, cart);
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setGoodsName((goods.getName()));
            if (StringUtils.isEmpty(product.getUrl())) {
                cart.setPicUrl(goods.getPicUrl());
            } else {
                cart.setPicUrl(product.getUrl());
            }
            cart.setPrice(product.getPrice());
            cart.setSpecifications(product.getSpecifications());
            cart.setUserId(userId);
            cart.setChecked(BasicEnum.YES.getKey());
            baseMapper.insertSelective(cart);
            return cart.getId();
        } else {
            //取得规格的信息,判断规格库存
            int num = existCart.getNumber() + number;
            AssertUtil.isNotTrue(num > product.getNumber(), "库存不足");
            existCart.setNumber(num);
            if (baseMapper.updateByPrimaryKeySelective(existCart) == 0) {
                throw new BusinessException("添加失败");
            }
            return existCart.getId();
        }
    }

    @Override
    public void updateCart(Long userId, CartDTO cartDTO) {
        AssertUtil.notNull(cartDTO.getId(), "操作异常");
        Long productId = cartDTO.getProductId();
        Integer number = cartDTO.getNumber();
        Long goodsId = cartDTO.getGoodsId();

        //判断是否存在该订单
        // 如果不存在，直接返回错误
        Cart existCart = baseMapper.selectByPrimaryKey(cartDTO.getId());
        AssertUtil.notNull(existCart, "数据不存在");
        AssertUtil.isTrue(existCart.getUserId().equals(userId), "无权限操作数据");

        // 判断goodsId和productId是否与当前cart里的值一致
        AssertUtil.isTrue(existCart.getGoodsId().equals(goodsId), "数据异常");
        AssertUtil.isTrue(existCart.getProductId().equals(productId), "数据异常");

        //判断商品是否可以购买
        Goods goods = goodsMapper.selectByPrimaryKey(goodsId);
        AssertUtil.isNotTrue(goods == null || BasicEnum.NO.getKey().equals(goods.getIsOnSale()), "商品已下架");

        GoodsProduct product = goodsProductService.getById(productId);
        AssertUtil.notNull(product, "货品不存在");

        //取得规格的信息,判断规格库存
        AssertUtil.isNotTrue(number > product.getNumber(), "库存不足");

        existCart.setNumber(number);
        if (baseMapper.updateByPrimaryKeySelective(existCart) == 0) {
            throw new BusinessException("操作失败");
        }
    }

    @Override
    public void delete(Long userId, List<Integer> productIds) {
        AssertUtil.isTrue(productIds.size() > 0, "请选择删除商品");
        if (baseMapper.deleteByUserIdAndProductIds(userId, productIds) == 0) {
            throw new BusinessException("删除失败");
        }
    }

    @Override
    public CartCheckoutVO checkout(Long userId, Long cartId, Long addressId, Long couponId, Long userCouponId, Long grouponRulesId) {
        // 收货地址
        Address checkedAddress = null;
        if (addressId != null && !addressId.equals(0)) {
            checkedAddress = addressMapper.getByIdAndUserId(userId, addressId);
        }
        if (checkedAddress == null) {
            checkedAddress = addressMapper.getDefautlByUserId(userId);
            // 如果仍然没有地址，则是没有收货地址
            // 返回一个空的地址id=0，这样前端则会提醒添加地址
            if (checkedAddress == null) {
                checkedAddress = new Address();
                checkedAddress.setId(0L);
                addressId = 0L;
            } else {
                addressId = checkedAddress.getId();
            }
        }

        // 团购优惠
        BigDecimal grouponPrice = new BigDecimal(0.00);
        GrouponRules grouponRules = grouponRulesMapper.selectByPrimaryKey(grouponRulesId);
        if (grouponRules != null) {
            grouponPrice = grouponRules.getDiscount();
        }

        // 商品价格
        List<Cart> checkedGoodsList;
        if (cartId == null || cartId.equals(0L)) {
            checkedGoodsList = baseMapper.listByUserId(userId);
        } else {
            Cart cart = baseMapper.selectByPrimaryKey(cartId);
            AssertUtil.notNull(cart, "购物车商品不存在");
            AssertUtil.isTrue(cart.getUserId().equals(userId), "操作异常");
            checkedGoodsList = new ArrayList<>(1);
            checkedGoodsList.add(cart);
        }
        BigDecimal checkedGoodsPrice = new BigDecimal(0.00);
        for (Cart cart : checkedGoodsList) {
            //  只有当团购规格商品ID符合才进行团购优惠
            if (grouponRules != null && grouponRules.getGoodsId().equals(cart.getGoodsId())) {
                checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().subtract(grouponPrice).multiply(new BigDecimal(cart.getNumber())));
            } else {
                checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            }
        }

        // 计算优惠券可用情况
        BigDecimal tmpCouponPrice = new BigDecimal(0.00);
        Long tmpCouponId = 0L;
        Long tmpUserCouponId = 0L;
        int tmpCouponLength = 0;
        List<CouponUser> couponUserList = couponUserMapper.listByUserId(userId);
        for (CouponUser couponUser : couponUserList) {
            Coupon coupon = couponVerifyService.checkCoupon(userId, couponUser.getCouponId(), couponUser.getId(), checkedGoodsPrice, checkedGoodsList);
            if (coupon == null) {
                continue;
            }

            tmpCouponLength++;
            if (tmpCouponPrice.compareTo(coupon.getDiscount()) == -1) {
                tmpCouponPrice = coupon.getDiscount();
                tmpCouponId = coupon.getId();
                tmpUserCouponId = couponUser.getId();
            }
        }
        // 获取优惠券减免金额，优惠券可用数量
        int availableCouponLength = tmpCouponLength;
        BigDecimal couponPrice = new BigDecimal(0);
        // 这里存在三种情况
        // 1. 用户不想使用优惠券，则不处理
        // 2. 用户想自动使用优惠券，则选择合适优惠券
        // 3. 用户已选择优惠券，则测试优惠券是否合适
        if (couponId == null || couponId.equals(-1)) {
            couponId = -1L;
            userCouponId = -1L;
        } else if (couponId.equals(0)) {
            couponPrice = tmpCouponPrice;
            couponId = tmpCouponId;
            userCouponId = tmpUserCouponId;
        } else {
            Coupon coupon = couponVerifyService.checkCoupon(userId, couponId, userCouponId, checkedGoodsPrice, checkedGoodsList);
            // 用户选择的优惠券有问题，则选择合适优惠券，否则使用用户选择的优惠券
            if (coupon == null) {
                couponPrice = tmpCouponPrice;
                couponId = tmpCouponId;
                userCouponId = tmpUserCouponId;
            } else {
                couponPrice = coupon.getDiscount();
            }
        }

        // 根据订单商品总价计算运费，满88则免运费，否则8元；
        BigDecimal freightPrice = new BigDecimal(0.00);
        if (checkedGoodsPrice.compareTo(configHelper.getFreightLimit()) < 0) {
            freightPrice = configHelper.getFreight();
        }

        // 可以使用的其他钱，例如用户积分
        BigDecimal integralPrice = new BigDecimal(0.00);

        // 订单费用
        BigDecimal orderTotalPrice = checkedGoodsPrice.add(freightPrice).subtract(couponPrice).max(new BigDecimal(0.00));

        BigDecimal actualPrice = orderTotalPrice.subtract(integralPrice);

        CartCheckoutVO vo = new CartCheckoutVO();
        vo.setAddressId(addressId);
        vo.setCouponId(couponId);
        vo.setUserCouponId(userCouponId);
        vo.setCartId(cartId);
        vo.setGrouponPrice(grouponPrice);
        vo.setGrouponRulesId(grouponRulesId);
        vo.setActualPrice(actualPrice);
        vo.setCheckedAddress(checkedAddress);
        vo.setAvailableCouponLength(availableCouponLength);
        vo.setFreightPrice(freightPrice);
        vo.setGoodsTotalPrice(checkedGoodsPrice);
        vo.setCouponPrice(couponPrice);
        vo.setOrderTotalPrice(orderTotalPrice);
        vo.setActualPrice(actualPrice);
        vo.setCheckedGoodsList(checkedGoodsList);
        return vo;
    }

    @Override
    public void updateCheck(Long userId, List<Long> productIds, Integer checked) {
        baseMapper.updateCheck(userId, productIds, checked);
    }

    @Override
    public void deleteById(Long cartId) {
        baseMapper.deleteLogicalByPrimaryKey(cartId);
    }

    @Override
    public void clearGoods(Long userId) {
        baseMapper.deleteByUserId(userId);
    }
}