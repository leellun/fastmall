package com.newland.mall.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * 地区
 */
@Data
public class RegionVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String name;
    private Integer type;
    private Integer code;
    private List<RegionVO> children;
}
