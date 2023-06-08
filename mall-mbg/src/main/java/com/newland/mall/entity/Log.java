package com.newland.mall.entity;

import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 操作日志表
 * mall_log
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="操作日志表")
public class Log  extends BaseEntity implements Serializable {

    /**
     * 管理员
     */
    @Schema(name ="管理员")
    private String admin;

    /**
     * 管理员地址
     */
    @Schema(name ="管理员地址")
    private String ip;

    /**
     * 操作分类
     */
    @Schema(name ="操作分类")
    private Integer type;

    /**
     * 操作动作
     */
    @Schema(name ="操作动作")
    private String action;

    /**
     * 操作状态
     */
    @Schema(name ="操作状态")
    private Integer status;

    /**
     * 操作结果，或者成功消息，或者失败消息
     */
    @Schema(name ="操作结果，或者成功消息，或者失败消息")
    private String result;

    /**
     * 补充信息
     */
    @Schema(name ="补充信息")
    private String comment;


    private static final long serialVersionUID = 1L;
}