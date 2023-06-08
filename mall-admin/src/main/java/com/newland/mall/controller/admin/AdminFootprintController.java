package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Footprint;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.FootprintService;
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

@Tag(name = "用户足迹")
@RestController
@RequestMapping("/admin/footprint")
@Validated
public class AdminFootprintController {
    @Autowired
    private FootprintService footprintService;

    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<Footprint>> list(Long userId, Long goodsId,
                                                  @RequestParam(defaultValue = "1") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                  @Sort @RequestParam(defaultValue = "create_time") String order,
                                                  @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<Footprint> footprintList = footprintService.listFootprints(userId, goodsId, pageEntity);
        return RestResponse.ok(footprintList);
    }
}
