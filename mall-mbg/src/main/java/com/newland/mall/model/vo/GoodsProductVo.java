package com.newland.mall.model.vo;

import com.newland.mall.entity.GoodsProduct;
import com.newland.mall.entity.GoodsProductSpecRelation;
import lombok.Data;

import java.util.List;

/**
 * product
 *
 * @author leell
 * @date 2023/6/11 15:54:34
 */
@Data
public class GoodsProductVo extends GoodsProduct {
    private List<GoodsProductSpecRelation> goodsProductSpecRelations;
}
