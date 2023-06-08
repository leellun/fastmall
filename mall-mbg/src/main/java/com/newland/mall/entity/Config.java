package com.newland.mall.entity;

import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 系统配置表
 * mall_system
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="系统配置表")
public class Config extends BaseEntity implements Serializable {

    /**
     * 系统配置名
     */
    @Schema(name ="系统配置名")
    private String keyName;

    /**
     * 系统配置值
     */
    @Schema(name ="系统配置值")
    private String keyValue;
    /**
     * @see com.newland.mall.enums.ConfigTypeEnum
     */
    @Schema(name = "配置类型 1 商城 2 运费 3 订单 4 小程序")
    private Integer type;
    private static final long serialVersionUID = 1L;
}