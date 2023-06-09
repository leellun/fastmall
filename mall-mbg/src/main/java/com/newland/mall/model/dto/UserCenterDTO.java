package com.newland.mall.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.validator.IntOptions;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 用户中心修改
 * </p>
 *
 */
@Data
public class UserCenterDTO implements Serializable {
    @NotNull(message = "id不能为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    @Schema(name= "昵称", required = true)
    @NotBlank(message = "昵称不能为空")
    private String nickName;
    @Schema(name="手机号", required = true)
    @NotBlank(message = "手机号不能为空")
    private String phone;
    @Schema(name= "性别", required = true)
    @NotBlank(message = "性别不能为空")
    @IntOptions(options = {1,0},message = "性别参数不正确")
    private Integer gender;
}
