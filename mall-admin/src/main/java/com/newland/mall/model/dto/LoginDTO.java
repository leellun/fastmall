package com.newland.mall.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * Author: leell
 * Date: 2023/2/28 01:20:10
 */
@Data
public class LoginDTO {
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;

}
