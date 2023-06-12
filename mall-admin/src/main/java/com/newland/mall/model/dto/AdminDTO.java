package com.newland.mall.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.newland.mall.validator.Insert;
import com.newland.mall.validator.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 改名
 * Author: leell
 * Date: 2023/2/28 00:21:22
 */
@Schema(name = "管理员")
@Data
public class AdminDTO {
    /**
     */
    @NotNull(message = "id不能为空",groups = Update.class)
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;

    /**
     * 管理员名称
     */
    @Schema(name ="管理员名称")
    @NotEmpty(message = "管理员名称不能为空",groups = {Insert.class,Update.class})
    @Pattern(regexp =  "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$",message = "管理员名称不符合规定",groups = {Insert.class,Update.class})
    private String username;

    /**
     * 管理员密码
     */
    @Schema(name ="管理员密码")
    @NotEmpty(message = "管理员密码不能为空",groups = {Insert.class,Update.class})
    @Min(value = 6,message = "管理员密码长度不能小于6",groups = {Insert.class,Update.class})
    private String password;

    /**
     * 最近一次登录IP地址
     */
    @Schema(name ="最近一次登录IP地址")
    private String lastLoginIp;

    /**
     * 最近一次登录时间
     */
    @Schema(name ="最近一次登录时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    /**
     * 头像图片
     */
    @Schema(name ="头像图片")
    private String avatar;

    /**
     * 创建时间
     */
    @Schema(name ="创建时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(name ="更新时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @Schema(name ="逻辑删除")
    private Integer deleted;

    /**
     * 角色列表
     */
    @Schema(name ="角色列表")
    private String roleIds;
}
