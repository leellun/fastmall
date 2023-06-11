package com.newland.mall.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 商品基本信息表
 * mall_goods
 * @author leell
 * @date 2023-06-11 15:40:56
 */
@Data
@Schema(name ="商品基本信息表")
public class Goods implements Serializable {
    /**
     * 主键
     */
    @Schema(name ="主键")
    private Long id;

    /**
     * 商品名称
     */
    @Schema(name ="商品名称")
    private String name;

    /**
     * 商品所属类目ID
     */
    @Schema(name ="商品所属类目ID")
    private Long categoryId;

    /**
     * 属性分组id
     */
    @Schema(name ="属性分组id")
    private Long goodsGroupId;

    /**
     * 分类
     */
    @Schema(name ="分类")
    private Long brandId;

    /**
     * 商品宣传图片列表，采用JSON数组格式
     */
    @Schema(name ="商品宣传图片列表，采用JSON数组格式")
    private String gallery;

    /**
     * 商品关键字，采用逗号间隔
     */
    @Schema(name ="商品关键字，采用逗号间隔")
    private String keywords;

    /**
     * 商品简介
     */
    @Schema(name ="商品简介")
    private String brief;

    /**
     * 是否上架
     */
    @Schema(name ="是否上架")
    private Boolean isOnSale;

    /**
     * 排序
     */
    @Schema(name ="排序")
    private Short sortOrder;

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
     */
    @Schema(name ="是否新品首发，如果设置则可以在新品首发页面展示")
    private Boolean isNew;

    /**
     * 是否人气推荐，如果设置则可以在人气推荐页面展示
     */
    @Schema(name ="是否人气推荐，如果设置则可以在人气推荐页面展示")
    private Boolean isHot;

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

    /**
     * 商品详细介绍，是富文本格式
     */
    @Schema(name ="商品详细介绍，是富文本格式")
    private String detail;

    private static final long serialVersionUID = 1L;
}