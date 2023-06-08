package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Aftersale;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.AftersaleService;
import com.newland.mall.validator.Order;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Tag(name = "售后管理")
@RestController
@RequestMapping("/admin/aftersale")
@Validated
public class AdminAftersaleController {

    @Autowired
    private AftersaleService aftersaleService;

    @Operation(summary = "售后查询")
    @GetMapping
    public RestResponse<PageInfo<Aftersale>> list(Long orderId, String aftersaleSn, Integer status,
                                                  @RequestParam(defaultValue = "1") Integer pageNo,
                                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                                  @Sort @RequestParam(defaultValue = "create_time") String order,
                                                  @Order @RequestParam(defaultValue = "false") Boolean desc) {
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setDesc(desc);
        pageEntity.setOrder(order);
        PageInfo<Aftersale> aftersaleList = aftersaleService.getAftersalePage(orderId, aftersaleSn, status, pageEntity);
        return RestResponse.ok(aftersaleList);
    }

    @Operation(summary = "审核通过")
    @PostMapping("/recept")
    public RestResponse recept(@RequestBody Aftersale aftersale) {
        aftersaleService.recept(aftersale);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "批量通过")
    @PostMapping("/batchRecept")
    public RestResponse batchRecept(@RequestBody String ids) {
        List<Long> idList = Arrays.stream(ids.split(",")).map(Long::valueOf).collect(Collectors.toList());
        aftersaleService.batchRecept(idList);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "审核拒绝")
    @PostMapping("/reject")
    public RestResponse reject(@RequestBody Aftersale aftersale) {
        aftersaleService.reject(aftersale);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "批量拒绝")
    @PostMapping("/batchReject")
    public RestResponse batchReject(@RequestBody List<Long> idList) {
        aftersaleService.batchReject(idList);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "退款")
    @PostMapping("/refund")
    public RestResponse refund(@RequestBody Aftersale aftersale) {
        aftersaleService.refund(aftersale);
        return RestResponse.success("操作成功");
    }
}
