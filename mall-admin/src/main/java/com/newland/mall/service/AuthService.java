package com.newland.mall.service;

import com.newland.mall.model.dto.LoginDTO;
import com.newland.mall.model.dto.wx.*;
import com.newland.mall.model.vo.wx.WebLoginVO;

/**
 * 认证接口
 * @author liulun
 * @date 2023/2/28 13:29
 */
public interface AuthService {

    /**
     * web用户登录
     * @param loginDTO
     * @param ipAddr
     */
    WebLoginVO webLogin(LoginDTO loginDTO, String ipAddr);


    /**
     * 发送验证码
     * @param mobile
     */
    void registerCaptcha(String mobile);

    /**
     * 注册
     * @param registerDTO
     * @param ipAddr
     */
    WebLoginVO register(RegisterDTO registerDTO, String ipAddr);

    /**
     * 发送验证码
     * @param userId
     * @param captchaDTO
     */
    void captcha(Long userId, CaptchaDTO captchaDTO);

    /**
     * 重置账号密码
     * @param userResetPassword
     */
    void reset(UserResetPassword userResetPassword);

    /**
     * 重置手机号码
     * @param userId
     * @param resetPhoneDTO
     */
    void resetPhone(Long userId, ResetPhoneDTO resetPhoneDTO);
}
