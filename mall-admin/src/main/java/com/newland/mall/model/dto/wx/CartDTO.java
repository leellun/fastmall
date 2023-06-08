package com.newland.mall.model.dto.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 购物车商品表
 * mall_cart
 *
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Schema(name = "购物车商品表")
public class CartDTO extends BaseEntity implements Serializable {
    @Schema(name = "购物车ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    /**
     * 商品表的商品ID
     */
    @NotNull(message = "数据不存在")
    @Schema(name = "商品表的商品ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long goodsId;

    /**
     * 商品货品表的货品ID
     */
    @NotNull(message = "数据不存在")
    @Schema(name = "商品货品表的货品ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long productId;

    /**
     * 商品货品的数量
     */
    @NotNull(message = "商品货品的数量不存在")
    @Min(value = 1, message = "数量不正确")
    @Schema(name = "商品货品的数量")
    private Integer number;

    private static final long serialVersionUID = 1L;
}