package com.newland.mall.model.dto;

import com.newland.mall.entity.Goods;
import com.newland.mall.entity.GoodsAttributeValue;
import com.newland.mall.entity.GoodsProduct;
import com.newland.mall.entity.GoodsSpecification;
import lombok.Data;

/**
 * 商品添加
 * @author leell
 */
@Data
public class GoodsAllinoneDTO {
    Goods goods;
    GoodsSpecification[] specifications;
    GoodsAttributeValue[] attributes;
    GoodsProduct[] products;
}
