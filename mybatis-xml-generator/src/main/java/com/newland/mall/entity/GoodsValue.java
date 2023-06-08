package com.newland.mall.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 商品参数表
 * mall_goods_value
 * @author leell
 * @date 2023-06-08 13:44:15
 */
@Data
@Schema(name ="商品参数表")
public class GoodsValue implements Serializable {
    /**
     * 商品参数值id
     */
    @Schema(name ="商品参数值id")
    private Long id;

    /**
     * 商品参数id
     */
    @Schema(name ="商品参数id")
    private Long goodsAttrId;

    /**
     * 商品参数名称
     */
    @Schema(name ="商品参数名称")
    private String name;

    /**
     * 商品参数值
     */
    @Schema(name ="商品参数值")
    private String value;

    /**
     * 排序
     */
    @Schema(name ="排序")
    private Integer attributeSort;

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