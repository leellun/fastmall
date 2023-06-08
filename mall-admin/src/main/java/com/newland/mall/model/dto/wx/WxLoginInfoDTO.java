package com.newland.mall.model.dto.wx;

import com.newland.mall.model.vo.wx.UserInfoVO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 微信登录
 */
@Data
public class WxLoginInfoDTO {
    @NotEmpty(message = "code不能为空")
    private String code;
    @NotNull(message = "用户信息不能为空")
    private UserInfoVO userInfo;
}
