package com.newland.security.utils;

import com.newland.mall.model.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author leell
 */
public class SecurityUtil {

    /**
     * 获取当前登录用户
     */
    public static LoginUser getLoginUser() {
        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (details instanceof LoginUser) {
            return (LoginUser) details;
        } else {
            return null;
        }
    }

    public static Long getUserId() {
        LoginUser loginUser = getLoginUser();
        return loginUser == null ? null : loginUser.getUserId();
    }

}
