package com.newland.mall.controller.admin;


import com.github.pagehelper.PageInfo;
import com.newland.mall.model.LoginUser;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.dto.LoginDTO;
import com.newland.mall.model.dto.SysUserDto;
import com.newland.mall.model.dto.UserPassVO;
import com.newland.mall.model.dto.UserQueryDTO;
import com.newland.mall.model.vo.SysUserItemVo;
import com.newland.mall.model.vo.SysUserVo;
import com.newland.mall.service.SysUserService;
import com.newland.mall.validator.Insert;
import com.newland.mall.validator.IntOptions;
import com.newland.mall.validator.Update;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * <p>
 * 系统用户 前端控制器
 * </p>
 *
 * @author leellun
 * @since 2022-12-06
 */
@Tag(name = "系统:用户管理")
@RestController
@RequestMapping("/admin/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Operation(summary = "用户登录")
    @Parameters({@Parameter(name = "loginDTO", description = "登录信息", required = true)})
    @PostMapping(value = "/login")
    public RestResponse<LoginUser> login(@RequestBody @Validated LoginDTO loginDTO) {
        return RestResponse.ok(sysUserService.login(loginDTO));
    }

    @Operation(summary = "查询用户")
    @Parameters({@Parameter(name = "userQueryDTO", description = "用户列表", required = true)})
    @PostMapping("/list")
    @PreAuthorize("hasAuthority('user:select')")
    public RestResponse<PageInfo<SysUserVo>> list(@RequestBody UserQueryDTO userQueryDTO) {
        return RestResponse.ok(sysUserService.getUsers(userQueryDTO));
    }

    @Operation(summary = "查询用户")
    @Parameters({@Parameter(name = "userQueryDTO", description = "用户列表", required = true)})
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:select')")
    public RestResponse<SysUserItemVo> list(@PathVariable Long id) {
        return RestResponse.ok(sysUserService.getUser(id));
    }

    @Operation(summary = "新增用户")
    @Parameters({@Parameter(name = "sysUser", description = "用户", required = true)})
    @PostMapping
    @PreAuthorize("hasAuthority('user:add')")
    public RestResponse add(@RequestBody @Validated(Insert.class) SysUserDto sysUserDto) {
        sysUserService.addUser(sysUserDto);
        return RestResponse.success("添加成功");
    }

    @Operation(summary = "修改用户")
    @Parameters({@Parameter(name = "sysUser", description = "用户", required = true)})
    @PutMapping
    @PreAuthorize("hasAuthority('user:update')")
    public RestResponse update(@RequestBody @Validated(Update.class) SysUserDto sysUserDto) {
        sysUserService.updateUser(sysUserDto);
        return RestResponse.success("更新成功");
    }

    @Operation(summary = "修改用户状态")
    @Parameters({@Parameter(name = "id", description = "用户id", required = true), @Parameter(name = "enable", description = "状态", required = true)})
    @PutMapping("/enable/{id}")
    @PreAuthorize("hasAuthority('user:update')")
    public RestResponse enable(@PathVariable("id") Long id, @RequestParam("enable") @Validated @IntOptions(options = {0, 1}, message = "状态不正确") Integer enable) {
        sysUserService.enableUser(id, enable);
        return RestResponse.success("更新成功");
    }

    @Operation(summary = "删除用户")
    @Parameters({@Parameter(name = "ids", description = "用户id列表", required = true)})
    @DeleteMapping
    @PreAuthorize("hasAuthority('user:delete')")
    public RestResponse delete(@RequestBody Set<Long> ids) {
        sysUserService.deleteUser(ids);
        return RestResponse.success("删除用户成功");
    }

    @Operation(summary = "修改密码")
    @Parameters({@Parameter(name = "userPassVO", description = "用户密码", required = true)})
    @PostMapping(value = "/updatePass")
    @PreAuthorize("hasAuthority('user:update')")
    public RestResponse updatePass(@RequestBody UserPassVO userPassVO) {
        sysUserService.updatePass(userPassVO);
        return RestResponse.success("密码修改成功");
    }

    @Operation(summary = "重置密码")
    @PutMapping(value = "/resetPass/{id}")
    @PreAuthorize("hasAuthority('user:update')")
    public RestResponse resetPass(@PathVariable Long id) {
        sysUserService.resetPass(id);
        return RestResponse.success("密码重置成功");
    }

    @Operation(summary = "上传头像")
    @PostMapping(value = "/updateAvatar")
    @PreAuthorize("hasAuthority('user:update')")
    public RestResponse<String> updateAvatar(@RequestParam String avatar) {
        sysUserService.updateAvatar(avatar);
        return RestResponse.success("头像上传成功");
    }
}

