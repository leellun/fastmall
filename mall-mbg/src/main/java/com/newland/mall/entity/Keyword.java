package com.newland.mall.entity;

import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 关键字表
 * mall_keyword
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="关键字表")
public class Keyword  extends BaseEntity implements Serializable {

    /**
     * 关键字
     */
    @Schema(name ="关键字")
    private String keyword;

    /**
     * 关键字的跳转链接
     */
    @Schema(name ="关键字的跳转链接")
    private String url;

    /**
     * 是否是热门关键字
     */
    @Schema(name ="是否是热门关键字")
    private Integer isHot;

    /**
     * 是否是默认关键字
     */
    @Schema(name ="是否是默认关键字")
    private Integer isDefault;

    /**
     * 排序
     */
    @Schema(name ="排序")
    private Integer sortOrder;

    private static final long serialVersionUID = 1L;
}