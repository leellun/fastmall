package com.newland.mall.controller;

import com.newland.mall.service.GoodsSpecService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品规格表(销售) 控制器
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@RestController
@RequestMapping("/goodsSpec")
@Tag(name = "商品规格表(销售)", description = "商品规格表(销售)")
public class GoodsSpecController {
    @Autowired
    private GoodsSpecService goodsSpecService;
}