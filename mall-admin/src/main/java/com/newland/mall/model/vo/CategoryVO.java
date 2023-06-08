package com.newland.mall.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String name;
    private String keywords;
    private String desc;
    private String iconUrl;
    private String picUrl;
    private String level;
    private List<CategoryVO> children;
}
