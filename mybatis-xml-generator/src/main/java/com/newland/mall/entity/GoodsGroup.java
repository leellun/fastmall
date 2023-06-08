package com.newland.mall.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 产品属性分类表
 * mall_goods_group
 * @author leell
 * @date 2023-06-08 13:44:15
 */
@Data
@Schema(name ="产品属性分类表")
public class GoodsGroup implements Serializable {
    /**
     * 分组id
     */
    @Schema(name ="分组id")
    private Long id;

    /**
     * 属性分类
     */
    @Schema(name ="属性分类")
    private String name;

    /**
     * 分类id
     */
    @Schema(name ="分类id")
    private Long categoryId;

    /**
     * 属性数量
     */
    @Schema(name ="属性数量")
    private Integer attributeCount;

    /**
     * 参数数量
     */
    @Schema(name ="参数数量")
    private Integer paramCount;

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