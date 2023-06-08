package com.newland.mall.controller;

import com.newland.mall.service.GoodsAttrService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品属性参数表 控制器
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@RestController
@RequestMapping("/goodsAttr")
@Tag(name = "商品属性参数表", description = "商品属性参数表")
public class GoodsAttrController {
    @Autowired
    private GoodsAttrService goodsAttrService;
}