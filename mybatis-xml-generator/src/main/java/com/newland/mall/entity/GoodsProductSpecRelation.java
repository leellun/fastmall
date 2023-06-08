package com.newland.mall.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 商品货品表-规格 中间表
 * mall_goods_product_spec_relation
 * @author leell
 * @date 2023-06-08 13:44:15
 */
@Data
@Schema(name ="商品货品表-规格 中间表")
public class GoodsProductSpecRelation implements Serializable {
    /**
     * 货品表-规格中间id
     */
    @Schema(name ="货品表-规格中间id")
    private Long id;

    /**
     * 规格ID
     */
    @Schema(name ="规格ID")
    private Long specId;

    /**
     * product id
     */
    @Schema(name ="product id")
    private Long productId;

    /**
     * 顺序
     */
    @Schema(name ="顺序")
    private Integer itemSort;

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

    /**
     * 商品规格名称
     */
    @Schema(name ="商品规格名称")
    private String name;

    /**
     * 商品规格值
     */
    @Schema(name ="商品规格值")
    private String value;

    private static final long serialVersionUID = 1L;
}