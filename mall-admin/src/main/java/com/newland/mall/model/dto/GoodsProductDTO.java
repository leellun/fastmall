package com.newland.mall.model.dto;

import com.newland.mall.entity.GoodsProduct;
import com.newland.mall.entity.GoodsProductSpecRelation;
import lombok.Data;

import java.util.List;

/**
 * 商品产品dto
 *
 * @author liulun
 * @date 2023/6/9 15:54
 */
@Data
public class GoodsProductDTO {
    private GoodsProduct goodsProduct;
    private List<GoodsProductSpecRelation> goodsSpecRelations;
}
