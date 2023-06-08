package com.newland.mall.service.impl;

import com.newland.mall.entity.GoodsSpec;
import com.newland.mall.mapper.GoodsSpecMapper;
import com.newland.mall.service.GoodsSpecService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 商品规格表(销售) 服务实现类
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Service
public class GoodsSpecServiceImpl extends ServiceImpl<GoodsSpecMapper, GoodsSpec> implements GoodsSpecService {
}