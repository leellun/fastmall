package com.newland.mall.controller;

import com.newland.mall.service.GoodsProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品货品表 控制器
 * @author leellun
 * @since 2023-06-13 00:00:11
 */
@RestController
@RequestMapping("/goodsProduct")
@Tag(name = "商品货品表", description = "商品货品表")
public class GoodsProductController {
    @Autowired
    private GoodsProductService goodsProductService;
}