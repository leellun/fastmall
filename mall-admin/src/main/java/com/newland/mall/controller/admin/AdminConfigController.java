package com.newland.mall.controller.admin;

import com.newland.mall.entity.Config;
import com.newland.mall.enums.ConfigTypeEnum;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.ConfigService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "商场配置")
@RestController
@RequestMapping("/admin/config")
@Validated
public class AdminConfigController {
    @Autowired
    private ConfigService configService;

    @Operation(summary = "商城配置列表")
    @GetMapping("/mall")
    public RestResponse<List<Config>> listMall() {
        return RestResponse.ok(configService.listMall());
    }

    @Operation(summary = "商场配置编辑")
    @PostMapping("/mall")
    public RestResponse updateMall(@RequestBody List<Config> configs) {
        configService.updateConfig(ConfigTypeEnum.MALL.getKey(),configs);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "运费配置详情")
    @GetMapping("/express")
    public RestResponse<List<Config>> listExpress() {
        List<Config> data = configService.listExpress();
        return RestResponse.ok(data);
    }

    @Operation(summary = "运费配置编辑")
    @PostMapping("/express")
    public RestResponse updateExpress(@RequestBody List<Config> data) {
        configService.updateConfig(ConfigTypeEnum.EXPRESS.getKey(),data);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "订单配置详情")
    @GetMapping("/order")
    public RestResponse<List<Config>> lisOrder() {
        List<Config> data = configService.listOrder();
        return RestResponse.ok(data);
    }

    @Operation(summary = "订单配置编辑")
    @PostMapping("/order")
    public RestResponse updateOrder(@RequestBody List<Config> data) {
        configService.updateConfig(ConfigTypeEnum.ORDER.getKey(),data);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "小程序配置详情")
    @GetMapping("/wx")
    public RestResponse<List<Config>> listWx() {
        List<Config> data = configService.listWx();
        return RestResponse.ok(data);
    }

    @Operation(summary = "小程序配置编辑")
    @PostMapping("/wx")
    public RestResponse updateWx(@RequestBody List<Config> data) {
        configService.updateConfig(ConfigTypeEnum.WX.getKey(),data);
        return RestResponse.success("操作成功");
    }
}
