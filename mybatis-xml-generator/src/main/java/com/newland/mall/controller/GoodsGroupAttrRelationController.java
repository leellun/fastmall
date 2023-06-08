package com.newland.mall.controller;

import com.newland.mall.service.GoodsGroupAttrRelationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 产品的分类和属性组的关系表，用于设置分类筛选条件（只支持一级分类） 控制器
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@RestController
@RequestMapping("/goodsGroupAttrRelation")
@Tag(name = "产品的分类和属性组的关系表，用于设置分类筛选条件（只支持一级分类）", description = "产品的分类和属性组的关系表，用于设置分类筛选条件（只支持一级分类）")
public class GoodsGroupAttrRelationController {
    @Autowired
    private GoodsGroupAttrRelationService goodsGroupAttrRelationService;
}