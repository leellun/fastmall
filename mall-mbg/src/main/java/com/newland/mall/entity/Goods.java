package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商品基本信息表
 * mall_goods
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="商品基本信息表")
public class Goods  extends BaseEntity implements Serializable {

    /**
     * 商品名称
     */
    @Schema(name ="商品名称")
    private String name;

    /**
     * 商品所属类目ID
     */
    @Schema(name ="商品所属类目ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long categoryId;
    /**
     * 属性分类id
     */
    @Schema(name ="商品所属类目ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long goodsGroupId;

    /**
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long brandId;

    /**
     * 商品宣传图片列表，采用JSON数组格式
     */
    @Schema(name ="商品宣传图片列表，采用JSON数组格式")
    private String[] gallery;

    /**
     * 商品关键字，采用逗号间隔
     */
    @Schema(name ="商品关键字，采用逗号间隔")
    private String[] keywords;

    /**
     * 商品简介
     */
    @Schema(name ="商品简介")
    private String brief;

    /**
     * 是否上架
     * @see com.newland.mall.enumeration.BasicEnum
     */
    @Schema(name ="是否上架")
    private Integer isOnSale;

    /**
     */
    private Integer sortOrder;

    /**
     * 商品页面商品图片
     */
    @Schema(name ="商品页面商品图片")
    private String picUrl;

    /**
     * 商品分享海报
     */
    @Schema(name ="商品分享海报")
    private String shareUrl;

    /**
     * 是否新品首发，如果设置则可以在新品首发页面展示
     * @see com.newland.mall.enumeration.BasicEnum
     */
    @Schema(name ="是否新品首发，如果设置则可以在新品首发页面展示")
    private Integer isNew;

    /**
     * 是否人气推荐，如果设置则可以在人气推荐页面展示
     * @see com.newland.mall.enumeration.BasicEnum
     */
    @Schema(name ="是否人气推荐，如果设置则可以在人气推荐页面展示")
    private Integer isHot;

    /**
     * 商品单位，例如件、盒
     */
    @Schema(name ="商品单位，例如件、盒")
    private String unit;

    /**
     * 专柜价格
     */
    @Schema(name ="专柜价格")
    private BigDecimal counterPrice;

    /**
     * 零售价格
     */
    @Schema(name ="零售价格")
    private BigDecimal retailPrice;


    /**
     * 商品详细介绍，是富文本格式
     */
    @Schema(name ="商品详细介绍，是富文本格式")
    private String detail;

    private static final long serialVersionUID = 1L;
}