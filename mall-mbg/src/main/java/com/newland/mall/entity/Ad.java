package com.newland.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 广告表
 * mall_ad
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="广告表")
public class Ad extends BaseEntity implements Serializable {

    /**
     * 广告标题
     */
    @Schema(name ="广告标题")
    private String name;

    /**
     * 所广告的商品页面或者活动页面链接地址
     */
    @Schema(name ="所广告的商品页面或者活动页面链接地址")
    private String link;

    /**
     * 广告宣传图片
     */
    @Schema(name ="广告宣传图片")
    private String url;

    /**
     * 广告位置：1则是首页
     */
    @Schema(name ="广告位置：1则是首页")
    private Integer position;

    /**
     * 活动内容
     */
    @Schema(name ="活动内容")
    private String content;

    /**
     * 广告开始时间
     */
    @Schema(name ="广告开始时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 广告结束时间
     */
    @Schema(name ="广告结束时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 是否启动
     */
    @Schema(name ="是否启动")
    private Integer enabled;

    private static final long serialVersionUID = 1L;
}