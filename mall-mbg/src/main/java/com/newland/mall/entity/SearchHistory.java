package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 搜索历史表
 * mall_search_history
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="搜索历史表")
public class SearchHistory  extends BaseEntity implements Serializable {

    /**
     * 用户表的用户ID
     */
    @Schema(name ="用户表的用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     * 搜索关键字
     */
    @Schema(name ="搜索关键字")
    private String keyword;

    /**
     * 搜索来源，如pc、wx、app
     */
    @Schema(name ="搜索来源，如pc、wx、app")
    private String source;

    private static final long serialVersionUID = 1L;
}