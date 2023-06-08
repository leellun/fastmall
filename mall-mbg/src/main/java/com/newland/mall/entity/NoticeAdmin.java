package com.newland.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通知管理员表
 * mall_notice_admin
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="通知管理员表")
public class NoticeAdmin  extends BaseEntity implements Serializable {

    /**
     * 通知ID
     */
    @Schema(name ="通知ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long noticeId;

    /**
     * 通知标题
     */
    @Schema(name ="通知标题")
    private String noticeTitle;

    /**
     * 接收通知的管理员ID
     */
    @Schema(name ="接收通知的管理员ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long adminId;

    /**
     * 阅读时间，如果是NULL则是未读状态
     */
    @Schema(name ="阅读时间，如果是NULL则是未读状态")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime readTime;

    private static final long serialVersionUID = 1L;
}