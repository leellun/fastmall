package com.newland.mall.controller;

import com.newland.mall.service.GoodsGroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品属性分类表 控制器
 * @author leellun
 * @since 2023-06-11 15:40:56
 */
@RestController
@RequestMapping("/goodsGroup")
@Tag(name = "产品属性分类表", description = "产品属性分类表")
public class GoodsGroupController {
    @Autowired
    private GoodsGroupService goodsGroupService;
}