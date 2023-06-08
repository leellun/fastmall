package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 收藏表
 * mall_collect
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="收藏表")
public class Collect extends BaseEntity implements Serializable {

    /**
     * 用户表的用户ID
     */
    @Schema(name ="用户表的用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     * 如果type=0，则是商品ID；如果type=1，则是专题ID
     */
    @Schema(name ="如果type=0，则是商品ID；如果type=1，则是专题ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long valueId;

    /**
     * 收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID
     * @see com.newland.mall.enums.CollectTypeEnum
     */
    @Schema(name ="收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID")
    private Integer type;

    private static final long serialVersionUID = 1L;
}