package com.newland.mall.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


/**
 * <p>
 * 角色-用户
 * </p>
 *
 */
@Data
public class UserRoleDTO {
    @NotNull(message = "角色不能为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long roleId;
    @NotNull(message = "用户不能为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;
}
