package com.newland.mall.service;

import com.newland.mall.model.dto.LoginDTO;
import com.newland.mall.model.dto.wx.WxLoginInfoDTO;
import com.newland.mall.model.vo.wx.WebLoginVO;

/**
 * 认证接口
 * @author liulun
 * @date 2023/2/28 13:29
 */
public interface AdminAuthService {
    /**
     * 获取验证码
     * @return
     */
    String getKaptcha();
    /**
     * 登录
     * @param loginDTO
     */
    String login(LoginDTO loginDTO);

}
