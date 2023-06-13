package com.newland.mall.entity;

import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 销售属性
 * mall_goods_sale_attr
 * @author leell
 * @date 2023-06-11 15:23:49
 */
@Data
@Schema(name ="销售属性")
public class GoodsSaleAttr extends BaseEntity implements Serializable {

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
     * 顺序
     */
    @Schema(name ="顺序")
    private Integer itemSort;
    private static final long serialVersionUID = 1L;
}