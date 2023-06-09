package com.newland.mall.service.impl;

import com.newland.mall.entity.GoodsCategoryAttrRelation;
import com.newland.mall.mapper.GoodsCategoryAttrRelationMapper;
import com.newland.mall.service.GoodsCategoryAttrRelationService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 产品的分类和属性的关系表，用于设置分类筛选条件（只支持一级分类） 服务实现类
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Service
public class GoodsCategoryAttrRelationServiceImpl extends ServiceImpl<GoodsCategoryAttrRelationMapper, GoodsCategoryAttrRelation> implements GoodsCategoryAttrRelationService {
}