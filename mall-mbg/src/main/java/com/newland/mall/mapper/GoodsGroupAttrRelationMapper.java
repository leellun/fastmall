package com.newland.mall.mapper;

import com.newland.mall.entity.GoodsGroupAttrRelation;
import com.newland.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 产品的分类和属性组的关系表，用于设置分类筛选条件（只支持一级分类） Mapper 接口
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Repository
public interface GoodsGroupAttrRelationMapper extends BaseMapper<GoodsGroupAttrRelation> {
}