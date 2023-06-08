package com.newland.mall.model.dto;

import lombok.Data;

/**
 * 重置密码
 * Author: leell
 * Date: 2023/3/19 21:19:59
 */
@Data
public class ResetPasswordDTO {
    private String oldPassword;
    private String newPassword;

}
