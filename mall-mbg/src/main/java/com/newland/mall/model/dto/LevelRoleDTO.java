package com.newland.mall.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mybatis.page.PageEntity;
import lombok.Data;

/**
 * 下属用户
 * Author: leell
 * Date: 2023/1/10 13:24:11
 */
@Data
public class LevelRoleDTO extends PageEntity {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long roleId;
    private String blurry;
}
