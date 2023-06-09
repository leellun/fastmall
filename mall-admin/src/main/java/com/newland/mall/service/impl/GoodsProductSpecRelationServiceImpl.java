package com.newland.mall.service.impl;

import com.newland.mall.entity.GoodsProductSpecRelation;
import com.newland.mall.mapper.GoodsProductSpecRelationMapper;
import com.newland.mall.service.GoodsProductSpecRelationService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 商品货品表-规格 中间表 服务实现类
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Service
public class GoodsProductSpecRelationServiceImpl extends ServiceImpl<GoodsProductSpecRelationMapper, GoodsProductSpecRelation> implements GoodsProductSpecRelationService {
}