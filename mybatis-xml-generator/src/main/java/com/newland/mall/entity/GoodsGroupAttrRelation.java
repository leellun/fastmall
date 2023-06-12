package com.newland.mall.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 产品的分类和属性组的关系表，用于设置分类筛选条件（只支持一级分类）
 * mall_goods_group_attr_relation
 * @author leell
 * @date 2023-06-13 00:00:11
 */
@Data
@Schema(name ="产品的分类和属性组的关系表，用于设置分类筛选条件（只支持一级分类）")
public class GoodsGroupAttrRelation implements Serializable {
    /**
     * 商品分类属性组关联id
     */
    @Schema(name ="商品分类属性组关联id")
    private Long id;

    /**
     * 属性id
     */
    @Schema(name ="属性id")
    private Long goodsAttrId;

    /**
     * 分组id
     */
    @Schema(name ="分组id")
    private Long goodsGroupId;

    /**
     * 分类id
     */
    @Schema(name ="分类id")
    private Long goodsCategoryId;

    /**
     * 创建时间
     */
    @Schema(name ="创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(name ="更新时间")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @Schema(name ="逻辑删除")
    private Boolean deleted;

    private static final long serialVersionUID = 1L;
}