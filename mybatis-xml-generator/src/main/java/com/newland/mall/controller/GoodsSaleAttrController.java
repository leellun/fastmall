package com.newland.mall.controller;

import com.newland.mall.service.GoodsSaleAttrService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 销售属性 控制器
 * @author leellun
 * @since 2023-06-13 00:00:11
 */
@RestController
@RequestMapping("/goodsSaleAttr")
@Tag(name = "销售属性", description = "销售属性")
public class GoodsSaleAttrController {
    @Autowired
    private GoodsSaleAttrService goodsSaleAttrService;
}