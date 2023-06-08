package com.newland.mall.model.vo;

import com.newland.mall.entity.SysUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 用户详情
 * Author: leell
 * Date: 2022/12/7 14:16:15
 */
@Data
@Schema(name = "用户返回对象")
public class SysUserItemVo extends SysUser {
    @Schema(name = "角色id")
    private List<Long> roleIds;
}
