package com.newland.mall.controller;

import com.newland.mall.service.GoodsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品基本信息表 控制器
 * @author leellun
 * @since 2023-06-11 15:40:56
 */
@RestController
@RequestMapping("/goods")
@Tag(name = "商品基本信息表", description = "商品基本信息表")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
}