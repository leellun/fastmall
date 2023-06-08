package com.newland.mall.model.dto.wx;

import lombok.Data;

/**
 * 用户profile更新
 * @author liulun
 * @date 2023/4/3 13:58
 */
@Data
public class UserProfileDTO {
    private String avatar;
    private Integer gender;
    private String nickname;
}
