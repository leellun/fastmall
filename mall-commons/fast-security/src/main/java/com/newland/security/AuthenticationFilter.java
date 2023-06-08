package com.newland.security;

import com.alibaba.fastjson2.JSON;
import com.newland.mall.enumeration.ResultCode;
import com.newland.mall.exception.BusinessException;
import com.newland.mall.model.LoginUser;
import com.newland.mall.utils.Base64Utils;
import com.newland.mall.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;

import java.io.IOException;

/**
 * 认证过滤器
 * @author leell
 */
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String headerStr = request.getHeader("Authorization");
        if (StringUtils.hasText(headerStr)) {
            String json = getToken(headerStr);
            json = JwtTokenUtil.getContentByToken(json);
            LoginUser user = JSON.parseObject(json, LoginUser.class);
            //身份信息、权限信息填充到用户身份token对象中
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null,
                    user.getAuthorities() == null ? AuthorityUtils.NO_AUTHORITIES : AuthorityUtils.createAuthorityList(user.getAuthorities().toArray(new String[]{})));
            //创建details
            authenticationToken.setDetails(user);
            //将authenticationToken填充到安全上下文
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 获取token
     */
    private String getToken(String tokenStr) {
        String token = tokenStr.split(" ")[1];
        if (!StringUtils.hasText(token)) {
            return null;
        }
        return token;
    }
}
