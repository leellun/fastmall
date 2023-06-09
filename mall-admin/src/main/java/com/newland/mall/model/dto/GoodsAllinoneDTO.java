package com.newland.mall.model.dto;

import com.newland.mall.entity.Goods;
import com.newland.mall.entity.GoodsAttrValue;
import com.newland.mall.entity.GoodsProduct;
import com.newland.mall.entity.GoodsSpec;
import lombok.Data;

/**
 * 商品添加
 * @author leell
 */
@Data
public class GoodsAllinoneDTO {
    Goods goods;
    GoodsSpec[] specifications;
    GoodsAttrValue[] attributes;
    GoodsProduct[] products;
}
