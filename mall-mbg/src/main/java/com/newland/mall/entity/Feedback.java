package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 意见反馈表
 * mall_feedback
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="意见反馈表")
public class Feedback  extends BaseEntity implements Serializable {

    /**
     * 用户表的用户ID
     */
    @Schema(name ="用户表的用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     * 用户名称
     */
    @Schema(name ="用户名称")
    private String username;

    /**
     * 手机号
     */
    @Schema(name ="手机号")
    private String mobile;

    /**
     * 反馈类型
     */
    @Schema(name ="反馈类型")
    private String feedType;

    /**
     * 反馈内容
     */
    @Schema(name ="反馈内容")
    private String content;

    /**
     * 状态
     * @see com.newland.mall.enumeration.BasicEnum
     */
    @Schema(name ="状态")
    private Integer status;

    /**
     * 是否含有图片
     * @see com.newland.mall.enumeration.BasicEnum
     */
    @Schema(name ="是否含有图片")
    private Integer hasPicture;

    /**
     * 图片地址列表，采用JSON数组格式
     */
    @Schema(name ="图片地址列表，采用JSON数组格式")
    private String[] picUrls;

    private static final long serialVersionUID = 1L;
}