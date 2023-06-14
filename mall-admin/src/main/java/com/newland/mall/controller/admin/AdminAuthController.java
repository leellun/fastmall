package com.newland.mall.controller.admin;

import com.newland.mall.model.LoginUser;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.dto.LoginDTO;
import com.newland.mall.service.AdminAuthService;
import com.newland.security.constant.HttpSessionConstant;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户认证")
@RestController
@RequestMapping("/admin/auth")
@Validated
public class AdminAuthController {
    @Autowired
    private AdminAuthService adminAuthService;

    @PostMapping("/login")
    public RestResponse<String> login(@RequestBody @Validated LoginDTO loginDTO, HttpSession httpSession) {
        String token = adminAuthService.login(loginDTO);
        return RestResponse.ok("登录成功！", token);
    }

    @PostMapping("/logout")
    public Object logout(HttpSession httpSession) {
        return null;
    }

}
