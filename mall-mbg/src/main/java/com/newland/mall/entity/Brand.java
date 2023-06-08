package com.newland.mall.entity;

import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 品牌商表
 * mall_brand
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="品牌商表")
public class Brand  extends BaseEntity implements Serializable {

    /**
     * 品牌商名称
     */
    @Schema(name ="品牌商名称")
    private String name;

    /**
     * 品牌商简介
     */
    @Schema(name ="品牌商简介")
    private String description;

    /**
     * 品牌商页的品牌商图片
     */
    @Schema(name ="品牌商页的品牌商图片")
    private String picUrl;

    /**
     */
    private Integer sortOrder;

    /**
     * 品牌商的商品低价，仅用于页面展示
     */
    @Schema(name ="品牌商的商品低价，仅用于页面展示")
    private BigDecimal floorPrice;


    private static final long serialVersionUID = 1L;
}