package com.newland.mall.model.dto.wx;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 重置手机号码
 * @author liulun
 * @date 2023/4/3 13:50
 */
@Data
public class ResetPhoneDTO {
    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^[1]\\d{10}$", message = "手机号不正确")
    private String mobile;
    @NotEmpty(message = "密码不能为空")
    private String password;
    @NotEmpty(message = "验证码不能为空")
    private String code;
}
