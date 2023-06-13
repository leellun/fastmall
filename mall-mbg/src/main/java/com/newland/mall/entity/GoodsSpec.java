package com.newland.mall.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品规格表(销售)
 * mall_goods_spec
 *
 * @author leell
 * @date 2023-06-08 13:44:15
 */
@Data
@Schema(name = "商品规格表(销售)")
public class GoodsSpec extends BaseEntity implements Serializable {

    /**
     * 商品表的商品ID
     */
    @Schema(name = "商品表的商品ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long goodsId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long saleAttrId;

    /**
     * sku id
     */
    @Schema(name = "sku id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long productId;

    /**
     * 商品参数id
     */
    @Schema(name = "商品参数id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long goodsAttrId;

    /**
     * 值id
     */
    @Schema(name = "值id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long goodsValueId;

    /**
     * 商品规格名称
     */
    @Schema(name = "商品规格名称")
    private String name;

    /**
     * 商品规格值
     */
    @Schema(name = "商品规格值")
    private String value;
    /**
     * 规格图片
     */
    @Schema(name = "规格图片")
    private String picUrl;

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String key() {
        return this.name + "=" + this.value;
    }

}