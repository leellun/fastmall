package com.newland.mall.mapper;

import com.newland.mall.entity.Cart;
import com.newland.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 购物车商品表 Mapper 接口
 * @author leellun
 * @since 2023-06-11 15:40:56
 */
@Repository
public interface CartMapper extends BaseMapper<Cart> {
}