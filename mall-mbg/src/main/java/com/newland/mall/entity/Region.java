package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 行政区域表
 * mall_region
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="行政区域表")
public class Region implements Serializable {
    /**
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0
     */
    @Schema(name ="行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long pid;

    /**
     * 行政区域名称
     */
    @Schema(name ="行政区域名称")
    private String name;

    /**
     * 行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县
     */
    @Schema(name ="行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县")
    private Integer type;

    /**
     * 行政区域编码
     */
    @Schema(name ="行政区域编码")
    private Integer code;

    private static final long serialVersionUID = 1L;
}