package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通知表
 * mall_notice
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="通知表")
public class Notice  extends BaseEntity implements Serializable {

    /**
     * 通知标题
     */
    @Schema(name ="通知标题")
    private String title;

    /**
     * 通知内容
     */
    @Schema(name ="通知内容")
    private String content;

    /**
     * 创建通知的管理员ID，如果是系统内置通知则是0.
     */
    @Schema(name ="创建通知的管理员ID，如果是系统内置通知则是0.")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long adminId;


    private static final long serialVersionUID = 1L;
}