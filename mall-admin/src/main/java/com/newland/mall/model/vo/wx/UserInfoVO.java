package com.newland.mall.model.vo.wx;

import lombok.Data;

@Data
public class UserInfoVO {
    private String nickName;
    private String avatarUrl;
    private String country;
    private String province;
    private String city;
    private String language;
    private Integer gender;
}
