package com.newland.mall.service.impl;

import com.newland.mall.entity.Cart;
import com.newland.mall.mapper.CartMapper;
import com.newland.mall.service.CartService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 购物车商品表 服务实现类
 * @author leellun
 * @since 2023-06-13 00:00:11
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
}