package com.newland.mall.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * 品牌
 * Author: leell
 * Date: 2023/3/18 20:37:14
 */
@Data
public class BrandVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long value = null;
    @JsonSerialize(using= ToStringSerializer.class)
    private String label = null;
}
