package com.newland.mall.service.impl;

import com.newland.mall.entity.GoodsProduct;
import com.newland.mall.mapper.GoodsProductMapper;
import com.newland.mall.service.GoodsProductService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 商品货品表 服务实现类
 * @author leellun
 * @since 2023-06-11 15:40:56
 */
@Service
public class GoodsProductServiceImpl extends ServiceImpl<GoodsProductMapper, GoodsProduct> implements GoodsProductService {
}