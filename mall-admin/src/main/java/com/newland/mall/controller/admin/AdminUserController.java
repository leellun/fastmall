package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.User;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.UserService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "会员管理")
@RestController
@RequestMapping("/admin/user")
@Validated
public class AdminUserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<User>> list(String username, String mobile,
                                             @RequestParam(defaultValue = "1") Integer pageNo,
                                             @RequestParam(defaultValue = "10") Integer pageSize,
                                             @Sort @RequestParam(defaultValue = "create_time") String order,
                                             @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<User> userList = userService.listUserPage(username, mobile, pageEntity);
        return RestResponse.ok(userList);
    }

    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public RestResponse<User> userDetail(@PathVariable Long id) {
        User user = userService.get(id);
        return RestResponse.ok(user);
    }

    @Operation(summary = "编辑")
    @PutMapping
    public RestResponse userUpdate(@RequestBody User user) {
        userService.update(user);
        return RestResponse.success("操作成功");
    }
}
