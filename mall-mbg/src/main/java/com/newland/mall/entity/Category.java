package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 类目表
 * mall_category
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="类目表")
public class Category extends BaseEntity implements Serializable {

    /**
     * 类目名称
     */
    @Schema(name ="类目名称")
    private String name;

    /**
     * 类目关键字，以JSON数组格式
     */
    @Schema(name ="类目关键字")
    private String keywords;

    /**
     * 类目广告语介绍
     */
    @Schema(name ="类目广告语介绍")
    private String description;

    /**
     * 父类目ID
     */
    @Schema(name ="父类目ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long pid;

    /**
     * 类目图标
     */
    @Schema(name ="类目图标")
    private String iconUrl;

    /**
     * 类目图片
     */
    @Schema(name ="类目图片")
    private String picUrl;

    /**
     */
    private String level;

    /**
     * 排序
     */
    @Schema(name ="排序")
    private Integer sortOrder;

    private static final long serialVersionUID = 1L;
}