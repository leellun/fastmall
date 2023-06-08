package com.newland.mall.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 商品规格表(销售)
 * mall_goods_spec
 * @author leell
 * @date 2023-06-08 13:44:15
 */
@Data
@Schema(name ="商品规格表(销售)")
public class GoodsSpec implements Serializable {
    /**
     * 规格id
     */
    @Schema(name ="规格id")
    private Long id;

    /**
     * 商品表的商品ID
     */
    @Schema(name ="商品表的商品ID")
    private Long goodsId;

    /**
     * sku id
     */
    @Schema(name ="sku id")
    private Long productId;

    /**
     * 商品参数id
     */
    @Schema(name ="商品参数id")
    private Long goodsAttrId;

    /**
     * 值id
     */
    @Schema(name ="值id")
    private Long goodsValueId;

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

    /**
     * 商品规格图片
     */
    @Schema(name ="商品规格图片")
    private String picUrl;

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