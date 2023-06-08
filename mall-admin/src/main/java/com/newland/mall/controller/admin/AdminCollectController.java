package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Collect;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.CollectService;
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

@Tag(name="用户收藏")
@RestController
@RequestMapping("/admin/collect")
@Validated
public class AdminCollectController {
    @Autowired
    private CollectService collectService;


    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<Collect>> list(Long userId, Long valueId,
                       @RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @Sort @RequestParam(defaultValue = "create_time") String order,
                       @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity=PageEntity.page(pageNo,pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<Collect> collectList = collectService.listCollect(userId, valueId,pageEntity);
        return RestResponse.ok(collectList);
    }
}
