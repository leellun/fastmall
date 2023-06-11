package com.newland.mall.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 商品货品表
 * mall_goods_product
 * @author leell
 * @date 2023-06-11 15:40:56
 */
@Data
@Schema(name ="商品货品表")
public class GoodsProduct implements Serializable {
    /**
     * 产品ID
     */
    @Schema(name ="产品ID")
    private Long id;

    /**
     * 商品表的商品ID
     */
    @Schema(name ="商品表的商品ID")
    private Long goodsId;

    /**
     * 商品编号
     */
    @Schema(name ="商品编号")
    private String productSn;

    /**
     * 商品货品价格
     */
    @Schema(name ="商品货品价格")
    private BigDecimal price;

    /**
     * 商品货品数量
     */
    @Schema(name ="商品货品数量")
    private Integer number;

    /**
     * 商品货品图片
     */
    @Schema(name ="商品货品图片")
    private String url;

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