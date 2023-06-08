package com.newland.mall.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.newland.mall.enumeration.BasicEnum;
import com.newland.mall.model.BaseEntity;
import com.newland.mall.validator.Insert;
import com.newland.mall.validator.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author leellun
 * @since 2023-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name ="系统用户")
public class SysUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Schema(name = "用户名")
    private String username;
    @Schema(name = "真实姓名")
    private String realName;

    @Schema(name = "昵称")
    private String nickName;

    /**
     * 性别
     * @see com.newland.mall.enums.GenderEnum
     */
    @Schema(name = "性别 1男 0女")
    private Integer gender;

    @Pattern(regexp = "^\\d{11}$",message ="手机号码格式不正确",groups = {Insert.class, Update.class})
    @Schema(name = "手机号码")
    private String phone;

    @Schema(name = "邮箱")
    private String email;

    @Schema(name = "头像地址")
    private String avatar;

    @Schema(name = "密码")
    private String password;

    @Schema(name = "状态：1启用、0禁用")
    private Integer enabled;

    @Schema(name = "是否可以删除：1可以、0不可以")
    private Integer canDeleted;

    /**
     * @see com.newland.mall.enumeration.BasicEnum
     */
    @Schema(name = "是否必须修改密码：1是、0否")
    private Integer mustResetPwd;

    @Schema(name = "密码连续错误次数")
    private Integer pwdFailsCount;

    @Schema(name = "密码错误锁定时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime failLockTime;

    @Schema(name = "修改密码的时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pwdResetTime;

    @Schema(name = "最后一次登陆时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

}
