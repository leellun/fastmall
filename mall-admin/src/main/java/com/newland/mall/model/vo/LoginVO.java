package com.newland.mall.model.vo;

import lombok.Data;

/**
 * @author liulun
 * @date 2023/2/28 13:31
 */
@Data
public class LoginVO {
    private String token;
    private AdminInfo adminInfo;

    @Data
    public static final class AdminInfo {
        private String nickName;
        private String avatar;
    }
}
