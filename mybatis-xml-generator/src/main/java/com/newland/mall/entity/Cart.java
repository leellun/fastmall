package com.newland.mall.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 购物车商品表
 * mall_cart
 * @author leell
 * @date 2023-06-11 15:40:56
 */
@Data
@Schema(name ="购物车商品表")
public class Cart implements Serializable {
    /**
     */
    private Long id;

    /**
     * 用户表的用户ID
     */
    @Schema(name ="用户表的用户ID")
    private Long userId;

    /**
     * 商品表的商品ID
     */
    @Schema(name ="商品表的商品ID")
    private Long goodsId;

    /**
     * 商品名称
     */
    @Schema(name ="商品名称")
    private String goodsName;

    /**
     * 商品货品表的货品ID
     */
    @Schema(name ="商品货品表的货品ID")
    private Long productId;

    /**
     * 商品编号
     */
    @Schema(name ="商品编号")
    private String productSn;

    /**
     * 1:有效 2：商品已下架 3：sku产品无效
     */
    @Schema(name ="1:有效 2：商品已下架 3：sku产品无效")
    private Boolean status;

    /**
     * 商品货品的价格
     */
    @Schema(name ="商品货品的价格")
    private BigDecimal price;

    /**
     * 商品货品的数量
     */
    @Schema(name ="商品货品的数量")
    private Integer number;

    /**
     * 购物车中商品是否选择状态
     */
    @Schema(name ="购物车中商品是否选择状态")
    private Boolean checked;

    /**
     * 商品图片或者商品货品图片
     */
    @Schema(name ="商品图片或者商品货品图片")
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