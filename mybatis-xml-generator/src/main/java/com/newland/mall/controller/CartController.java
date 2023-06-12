package com.newland.mall.controller;

import com.newland.mall.service.CartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 购物车商品表 控制器
 * @author leellun
 * @since 2023-06-13 00:00:11
 */
@RestController
@RequestMapping("/cart")
@Tag(name = "购物车商品表", description = "购物车商品表")
public class CartController {
    @Autowired
    private CartService cartService;
}