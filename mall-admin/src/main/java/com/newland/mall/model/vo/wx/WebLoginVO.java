package com.newland.mall.model.vo.wx;

import lombok.Data;

/**
 * web登录结果
 * Author: leell
 * Date: 2023/4/2 23:27:55
 */
@Data
public class WebLoginVO {
    private String token;
    private UserInfoVO userInfo;
}
