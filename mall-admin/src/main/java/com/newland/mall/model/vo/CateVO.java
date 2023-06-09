package com.newland.mall.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
public class CateVO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long value;
    private String label;
    private List<CateVO> children;
}
