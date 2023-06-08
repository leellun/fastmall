package com.newland.mall.service.impl;

import com.newland.mall.entity.GoodsGroup;
import com.newland.mall.mapper.GoodsGroupMapper;
import com.newland.mall.service.GoodsGroupService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 产品属性分类表 服务实现类
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Service
public class GoodsGroupServiceImpl extends ServiceImpl<GoodsGroupMapper, GoodsGroup> implements GoodsGroupService {
}