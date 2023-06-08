package com.newland.mall.model.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品分类对应属性信息
 */
@Schema(description="商品分类对应属性信息")
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodsAttrInfoVo {
    @Schema(description="商品属性ID")
    private Long attributeId;
    @Schema(description="商品属性分类ID")
    private Long attributeCategoryId;

}
