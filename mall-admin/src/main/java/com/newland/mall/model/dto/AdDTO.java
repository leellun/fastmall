package com.newland.mall.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.validator.Insert;
import com.newland.mall.validator.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 广告
 * Author: leell
 * Date: 2023/2/27 23:22:11
 */
@Schema(name = "广告")
@Data
public class AdDTO {
    /**
     * 广告id
     */
    @NotNull(message = "广告id不能为空",groups = Update.class)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 广告标题
     */
    @Schema(name ="广告标题")
    @NotEmpty(message = "广告标题不能为空",groups = {Insert.class, Update.class})
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
    @NotEmpty(message = "活动内容不能为空",groups = {Insert.class, Update.class})
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
}
