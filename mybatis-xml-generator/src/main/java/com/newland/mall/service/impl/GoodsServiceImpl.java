package com.newland.mall.service.impl;

import com.newland.mall.entity.Goods;
import com.newland.mall.mapper.GoodsMapper;
import com.newland.mall.service.GoodsService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 商品基本信息表 服务实现类
 * @author leellun
 * @since 2023-06-13 00:00:11
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
}