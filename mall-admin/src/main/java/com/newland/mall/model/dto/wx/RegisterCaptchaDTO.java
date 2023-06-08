package com.newland.mall.model.dto.wx;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 账号注册
 *
 * @author liulun
 * @date 2023/4/3 12:42
 */
@Data
public class RegisterCaptchaDTO {
    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^[1]\\d{10}$",message = "手机号不正确")
    private String mobile;
}
