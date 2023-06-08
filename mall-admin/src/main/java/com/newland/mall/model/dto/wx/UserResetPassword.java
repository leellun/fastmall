package com.newland.mall.model.dto.wx;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 账号密码重置
 *
 * @author liulun
 * @date 2023/4/3 13:40
 */
@Data
public class UserResetPassword {
    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^[1]\\d{10}$", message = "手机号不正确")
    private String mobile;
    @NotEmpty(message = "密码不能为空")
    private String password;
    @NotEmpty(message = "验证码不能为空")
    private String code;
}
