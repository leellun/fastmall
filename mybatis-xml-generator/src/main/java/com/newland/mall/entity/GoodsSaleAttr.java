package com.newland.mall.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 销售属性
 * mall_goods_sale_attr
 * @author leell
 * @date 2023-06-11 15:40:56
 */
@Data
@Schema(name ="销售属性")
public class GoodsSaleAttr implements Serializable {
    /**
     * 销售属性id
     */
    @Schema(name ="销售属性id")
    private Long id;

    /**
     * 商品表的商品ID
     */
    @Schema(name ="商品表的商品ID")
    private Long goodsId;

    /**
     * 商品参数id
     */
    @Schema(name ="商品参数id")
    private Long goodsAttrId;

    /**
     * 商品规格名称
     */
    @Schema(name ="商品规格名称")
    private String name;

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