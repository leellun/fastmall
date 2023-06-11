package com.newland.mall.service.impl;

import com.newland.mall.entity.GoodsGroupAttrRelation;
import com.newland.mall.mapper.GoodsGroupAttrRelationMapper;
import com.newland.mall.service.GoodsGroupAttrRelationService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 产品的分类和属性组的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * @author leellun
 * @since 2023-06-11 15:40:56
 */
@Service
public class GoodsGroupAttrRelationServiceImpl extends ServiceImpl<GoodsGroupAttrRelationMapper, GoodsGroupAttrRelation> implements GoodsGroupAttrRelationService {
}