package com.newland.mall.service.impl;

import com.newland.mall.entity.User;
import com.newland.mall.enums.UserGenderEnum;
import com.newland.mall.enums.UserLevelEnum;
import com.newland.mall.enums.UserStatusEnum;
import com.newland.mall.exception.BusinessException;
import com.newland.mall.mapper.UserMapper;
import com.newland.mall.model.LoginUser;
import com.newland.mall.model.dto.LoginDTO;
import com.newland.mall.model.dto.wx.CaptchaDTO;
import com.newland.mall.model.dto.wx.RegisterDTO;
import com.newland.mall.model.dto.wx.ResetPhoneDTO;
import com.newland.mall.model.dto.wx.UserResetPassword;
import com.newland.mall.model.vo.wx.UserInfoVO;
import com.newland.mall.model.vo.wx.WebLoginVO;
import com.newland.mall.notify.NotifyService;
import com.newland.mall.notify.NotifyType;
import com.newland.mall.service.AuthService;
import com.newland.mall.service.CouponUserService;
import com.newland.mall.service.UserService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mall.utils.CharUtil;
import com.newland.mall.utils.JsonUtil;
import com.newland.mall.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 登录服务
 * Author: leell
 * Date: 2023/3/6 23:57:06
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private CouponUserService couponUserService;
    @Autowired
    private NotifyService notifyService;

    @Override
    public WebLoginVO webLogin(LoginDTO loginDTO, String ipAddr) {
        User user = userMapper.getByUsername(loginDTO.getUsername());
        AssertUtil.notNull(user, "账号不存在");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        AssertUtil.isTrue(encoder.matches(loginDTO.getPassword(), user.getPassword()), "账号密码不对");

        // 更新登录情况
        user.setLastLoginTime(LocalDateTime.now());
        user.setLastLoginIp(ipAddr);
        userService.updateById(user);

        // userInfo
        UserInfoVO userInfo = new UserInfoVO();
        userInfo.setNickName(user.getNickname());
        userInfo.setAvatarUrl(user.getAvatar());

        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getId());

        String token = JwtTokenUtil.getToken(JsonUtil.toJSONString(loginUser));
        WebLoginVO webLoginVO = new WebLoginVO();
        webLoginVO.setToken(token);
        webLoginVO.setUserInfo(userInfo);
        return webLoginVO;
    }


    @Override
    public void registerCaptcha(String mobile) {
        AssertUtil.isTrue(notifyService.isSmsEnable(), "小程序后台验证码服务不支持");
        String code = CharUtil.getRandomNum(6);
//        boolean successful = CaptchaCodeManager.addToCache(phoneNumber, code);
//        AssertUtil.isTrue(successful,"验证码未超时1分钟，不能发送");
        notifyService.notifySmsTemplate(mobile, NotifyType.CAPTCHA, new String[]{code});
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public WebLoginVO register(RegisterDTO registerDTO, String ipAddr) {
        User dbUser = userMapper.getByUsername(registerDTO.getUsername());
        AssertUtil.isNull(dbUser, "用户名已注册");
        dbUser = userMapper.getByMobile(registerDTO.getMobile());
        AssertUtil.isNull(dbUser, "手机号已注册");
        //判断验证码是否正确
//        String cacheCode = CaptchaCodeManager.getCachedCaptcha(mobile);
//        AssertUtil.isNotTrue(registerDTO.getCode().equals(cacheCode),"验证码错误");
        // 非空，则是小程序注册
        // 继续验证openid

        User user = new User();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(registerDTO.getPassword());
        user.setUsername(registerDTO.getUsername());
        user.setPassword(encodedPassword);
        user.setMobile(registerDTO.getMobile());
//        user.setAvatar("https://yanxuan.nosdn.127.net/80841d741d7fa3073e0ae27bf487339f.jpg?imageView&quality=90&thumbnail=64x64");
        user.setNickname(registerDTO.getUsername());
        user.setGender(UserGenderEnum.UN_KNOWN.getKey());
        user.setUserLevel(UserLevelEnum.GENERAL_USER.getKey());
        user.setStatus(UserStatusEnum.AVAILABLE.getKey());
        user.setLastLoginTime(LocalDateTime.now());
        user.setLastLoginIp(ipAddr);
        userService.add(user);

        // 新用户发送注册优惠券
        couponUserService.assignForRegister(user.getId());

        // userInfo
        UserInfoVO userInfo = new UserInfoVO();
        userInfo.setNickName(registerDTO.getUsername());
        userInfo.setAvatarUrl(user.getAvatar());

        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getId());
        String token = JwtTokenUtil.getToken(JsonUtil.toJSONString(loginUser));
        WebLoginVO webLoginVO = new WebLoginVO();
        webLoginVO.setToken(token);
        webLoginVO.setUserInfo(userInfo);
        return webLoginVO;
    }

    @Override
    public void captcha(Long userId, CaptchaDTO captchaDTO) {
        AssertUtil.isTrue(notifyService.isSmsEnable(), "小程序后台验证码服务不支持");
        String code = CharUtil.getRandomNum(6);
//        boolean successful = CaptchaCodeManager.addToCache(phoneNumber, code);
//        AssertUtil.isTrue(successful,"验证码未超时1分钟，不能发送");
        notifyService.notifySmsTemplate(captchaDTO.getMobile(), NotifyType.CAPTCHA, new String[]{code});
    }

    @Override
    public void reset(UserResetPassword userResetPassword) {
        String password = userResetPassword.getPassword();
        String mobile = userResetPassword.getMobile();
        String code = userResetPassword.getCode();
        //判断验证码是否正确
//        String cacheCode = CaptchaCodeManager.getCachedCaptcha(mobile);
//        AssertUtil.isNotTrue(code.equals(cacheCode),"验证码错误");
        User dbUser = userMapper.getByMobile(mobile);
        AssertUtil.notNull(dbUser, "手机号未注册");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        dbUser.setPassword(encodedPassword);

        if (userService.updateById(dbUser) == 0) {
            throw new BusinessException("重置失败");
        }
    }

    @Override
    public void resetPhone(Long userId, ResetPhoneDTO resetPhoneDTO) {
        String password = resetPhoneDTO.getPassword();
        String mobile = resetPhoneDTO.getMobile();
        String code = resetPhoneDTO.getCode();

        //判断验证码是否正确
//        String cacheCode = CaptchaCodeManager.getCachedCaptcha(mobile);
//        AssertUtil.isNotTrue(code.equals(cacheCode),"验证码错误");

        User dbUser = userMapper.getByMobile(mobile);
        AssertUtil.isNull(dbUser, "手机号未注册");

        dbUser = userService.getById(userId);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        AssertUtil.isTrue(encoder.matches(password, dbUser.getPassword()), "账号密码不对");

        dbUser.setMobile(mobile);
        if (userService.updateById(dbUser) == 0) {
            throw new BusinessException("重置失败");
        }
    }
}
