package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Log;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.LogService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "操作日志")
@RestController
@RequestMapping("/admin/log")
@Validated
public class AdminLogController {
    @Autowired
    private LogService logService;

    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<Log>> list(String name,
                                            @RequestParam(defaultValue = "1") Integer pageNo,
                                            @RequestParam(defaultValue = "10") Integer pageSize,
                                            @Sort @RequestParam(defaultValue = "create_time") String order,
                                            @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<Log> logList = logService.listLogPage(name, pageEntity);
        return RestResponse.ok(logList);
    }
}
