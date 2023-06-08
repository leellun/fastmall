package com.newland.mall.controller.admin;

import com.newland.mall.model.RestResponse;
import com.newland.mall.service.GoodsProductService;
import com.newland.mall.service.GoodsService;
import com.newland.mall.service.OrderService;
import com.newland.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/dashboard")
@Validated
public class AdminDashbordController {
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsProductService productService;
    @Autowired
    private OrderService orderService;

    @GetMapping
    public Object info() {
        Long userTotal = userService.count();
        Long goodsTotal = goodsService.count();
        Long productTotal = productService.count();
        Long orderTotal = orderService.count();
        Map<String, Long> data = new HashMap<>();
        data.put("userTotal", userTotal);
        data.put("goodsTotal", goodsTotal);
        data.put("productTotal", productTotal);
        data.put("orderTotal", orderTotal);

        return RestResponse.ok(data);
    }

}
