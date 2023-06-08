package com.newland.mall.model.vo;

import com.newland.mall.entity.Goods;
import com.newland.mall.entity.GoodsAttributeValue;
import com.newland.mall.entity.GoodsProduct;
import com.newland.mall.entity.GoodsSpecification;
import lombok.Data;

import java.util.List;

/**
 * 商品详情
 * Author: leell
 * Date: 2023/3/18 18:13:50
 */
@Data
public class GoodsAllinoneVO {
    Goods goods;
    List<GoodsSpecification> specifications;
    List<GoodsAttributeValue> attributes;
    List<GoodsProduct> products;
    List<Long> categoryIds;
}
