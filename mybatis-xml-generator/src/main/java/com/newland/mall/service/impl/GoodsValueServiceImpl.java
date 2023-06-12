package com.newland.mall.service.impl;

import com.newland.mall.entity.GoodsValue;
import com.newland.mall.mapper.GoodsValueMapper;
import com.newland.mall.service.GoodsValueService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 商品参数表 服务实现类
 * @author leellun
 * @since 2023-06-13 00:00:11
 */
@Service
public class GoodsValueServiceImpl extends ServiceImpl<GoodsValueMapper, GoodsValue> implements GoodsValueService {
}