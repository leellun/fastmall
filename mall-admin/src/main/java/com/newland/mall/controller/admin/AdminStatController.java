package com.newland.mall.controller.admin;

import com.newland.mall.model.RestResponse;
import com.newland.mall.model.vo.StatGoodsVO;
import com.newland.mall.model.vo.StatOrderVO;
import com.newland.mall.model.vo.StatUserVO;
import com.newland.mall.service.StatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="统计管理")
@RestController
@RequestMapping("/admin/stat")
@Validated
public class AdminStatController {
    @Autowired
    private StatService statService;

    @Operation(summary = "用户统计")
    @GetMapping("/user")
    public RestResponse<List<StatUserVO>> statUser() {
        List<StatUserVO> rows = statService.statUser();
        return RestResponse.ok(rows);
    }

    @Operation(summary = "订单统计")
    @GetMapping("/order")
    public RestResponse<List<StatOrderVO>> statOrder() {
        return RestResponse.ok(statService.statOrder());
    }

    @Operation(summary = "商品统计")
    @GetMapping("/goods")
    public RestResponse<List<StatGoodsVO>> statGoods() {
        return RestResponse.ok( statService.statGoods());
    }

}
