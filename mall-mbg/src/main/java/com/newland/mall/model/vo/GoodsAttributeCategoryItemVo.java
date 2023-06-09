package com.newland.mall.model.vo;

import com.newland.mall.entity.GoodsAttr;
import com.newland.mall.entity.GoodsGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 包含有分类下属性的dto
 */
@Schema(description="商品属性分类")
@Data
public class GoodsAttributeCategoryItemVo extends GoodsGroup {
    @Schema(description = "商品属性列表")
    private List<GoodsAttr> goodsAttributeList;
}
