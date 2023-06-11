package com.newland.mall.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.newland.mall.entity.*;
import com.newland.mall.jackson.LongListSerializer;
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
    List<GoodsSaleAttrVo> goodsSaleAttrVos;
    List<GoodsAttrValue> attributes;
    List<GoodsProductVo> products;
    @JsonSerialize(using = LongListSerializer.class)
    List<Long> categoryIds;
}
