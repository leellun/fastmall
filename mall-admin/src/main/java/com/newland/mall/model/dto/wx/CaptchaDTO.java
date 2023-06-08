package com.newland.mall.model.dto.wx;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 请求验证码
 * @author liulun
 * @date 2023/4/3 13:36
 */
@Data
public class CaptchaDTO {
    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = "^[1]\\d{10}$",message = "手机号不正确")
    private String mobile;
    @NotEmpty(message = "type不能为空")
    private String type;
}
