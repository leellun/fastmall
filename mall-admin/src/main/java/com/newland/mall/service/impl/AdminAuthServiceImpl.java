package com.newland.mall.service.impl;

import com.newland.mall.entity.SysUser;
import com.newland.mall.mapper.SysUserMapper;
import com.newland.mall.model.LoginUser;
import com.newland.mall.model.dto.LoginDTO;
import com.newland.mall.service.AdminAuthService;
import com.newland.mall.service.SysMenuService;
import com.newland.mall.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 登录服务
 * Author: leell
 * Date: 2023/3/6 23:57:06
 */
@Service
public class AdminAuthServiceImpl implements AdminAuthService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMenuService sysMenuService;
    @Override
    public String getKaptcha() {
        return null;
    }

    @Override
    public String login(LoginDTO loginDTO) {
        SysUser sysUser = sysUserMapper.selectByUsername(loginDTO.getUsername());
        AssertUtil.notNull(sysUser, "用户不存在");
        String md5Password = Md5Util.encrypt(AesUtils.decrypt(loginDTO.getPassword()));
        AssertUtil.isTrue(md5Password.endsWith(sysUser.getPassword()), "密码不正确");
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(sysUser.getUsername());
        loginUser.setMobile(sysUser.getPhone());
        loginUser.setUserId(sysUser.getId());
        loginUser.setAuthorities(sysMenuService.getPermissions(sysUser.getId()));

        String token = JwtTokenUtil.getToken(JsonUtil.toJSONString(loginUser));
        return token;
    }

}
