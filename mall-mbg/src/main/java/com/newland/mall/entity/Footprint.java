package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户浏览足迹表
 * mall_footprint
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="用户浏览足迹表")
public class Footprint  extends BaseEntity implements Serializable {

    /**
     * 用户表的用户ID
     */
    @Schema(name ="用户表的用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     * 浏览商品ID
     */
    @Schema(name ="浏览商品ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long goodsId;

    private static final long serialVersionUID = 1L;
}