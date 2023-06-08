package com.newland.mall.model.vo.wx;

import com.newland.mall.entity.*;
import lombok.Data;
import org.springframework.boot.Banner;

import java.util.List;

/**
 * 首页数据
 * Author: leell
 * Date: 2023/4/5 23:49:23
 */
@Data
public class HomeIndexVO {
    /**
     * 首页广告
     */
    private List<Ad> banner;
    /**
     * 分类
     */
    private List<Category> channel;
    /**
     * 优惠卷推荐
     */
    private List<Coupon> couponList;
    /**
     * 新商品
     */
    private List<Goods> newGoodsList;
    /**
     * 热销商品
     */
    private List<Goods> hotGoodsList;
    /**
     * 首页品牌
     */
    private List<Brand> brandList;
    /**
     * 首页主题
     */
    private List<Topic> topicList;
    /**
     * 团购专区
     */
    private List<GrouponRuleVO> grouponList;
    /**
     * 非推荐分类商品
     */
    private List<CategoryGoodsVO> floorGoodsList;
}
