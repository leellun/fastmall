package com.newland.mall.entity;

import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户表
 * mall_user
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="用户表")
public class User  extends BaseEntity implements Serializable {

    /**
     * 用户名称
     */
    @Schema(name ="用户名称")
    private String username;

    /**
     * 用户密码
     */
    @Schema(name ="用户密码")
    private String password;

    /**
     * 性别：0 未知， 1男， 2 女
     * @see com.newland.mall.enums.UserGenderEnum
     */
    @Schema(name ="性别：0 未知， 1男， 2 女")
    private Integer gender;

    /**
     * 生日
     */
    @Schema(name ="生日")
    private LocalDate birthday;

    /**
     * 最近一次登录时间
     */
    @Schema(name ="最近一次登录时间")
    private LocalDateTime lastLoginTime;

    /**
     * 最近一次登录IP地址
     */
    @Schema(name ="最近一次登录IP地址")
    private String lastLoginIp;

    /**
     * 0 普通用户，1 VIP用户，2 高级VIP用户
     * @see com.newland.mall.enums.UserLevelEnum
     */
    @Schema(name ="0 普通用户，1 VIP用户，2 高级VIP用户")
    private Integer userLevel;

    /**
     * 用户昵称或网络名称
     */
    @Schema(name ="用户昵称或网络名称")
    private String nickname;

    /**
     * 用户手机号码
     */
    @Schema(name ="用户手机号码")
    private String mobile;

    /**
     * 用户头像图片
     */
    @Schema(name ="用户头像图片")
    private String avatar;

    /**
     * 微信登录openid
     */
    @Schema(name ="微信登录openid")
    private String weixinOpenid;

    /**
     * 微信登录会话KEY
     */
    @Schema(name ="微信登录会话KEY")
    private String sessionKey;

    /**
     * 0 可用, 1 禁用, 2 注销
     * @see com.newland.mall.enums.UserStatusEnum
     */
    @Schema(name ="0 可用, 1 禁用, 2 注销")
    private Integer status;

    private static final long serialVersionUID = 1L;
}