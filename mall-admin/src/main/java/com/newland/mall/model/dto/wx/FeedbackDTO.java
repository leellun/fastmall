package com.newland.mall.model.dto.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

/**
 * 意见反馈dto
 *
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name = "意见反馈表")
public class FeedbackDTO {

    /**
     * 用户表的用户ID
     */
    @Schema(name = "用户表的用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     * 手机号
     */
    @NotEmpty(message = "手机号码不能为空")
    @Pattern(regexp = "^[1]\\d{10}$",message = "手机号不正确")
    @Schema(name = "手机号")
    private String mobile;

    /**
     * 反馈类型
     */
    @NotEmpty(message = "反馈类型不能为空")
    @Schema(name = "反馈类型")
    private String feedType;

    /**
     * 反馈内容
     */
    @NotEmpty(message = "反馈内容不能为空")
    @Schema(name = "反馈内容")
    private String content;

    /**
     * 是否含有图片
     *
     * @see com.newland.mall.enumeration.BasicEnum
     */
    @Schema(name = "是否含有图片")
    private Integer hasPicture;

    /**
     * 图片地址列表，采用JSON数组格式
     */
    @Schema(name = "图片地址列表，采用JSON数组格式")
    private String[] picUrls;

    private static final long serialVersionUID = 1L;
}