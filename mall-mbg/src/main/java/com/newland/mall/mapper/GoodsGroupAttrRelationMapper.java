package com.newland.mall.mapper;

import com.newland.mall.entity.GoodsGroupAttrRelation;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品的分类和属性组的关系表，用于设置分类筛选条件（只支持一级分类） Mapper 接口
 *
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Repository
public interface GoodsGroupAttrRelationMapper extends BaseMapper<GoodsGroupAttrRelation> {
    /**
     * 通过分组删除
     *
     * @param gid 分组id
     */
    void deleteByGroupId(@Param("gid") Long gid);

    /**
     * 通过分组删除
     *
     * @param gid     分组id
     * @param attrIds 属性列表
     */
    void deleteByGroupIdAndAttrIds(@Param("gid") Long gid, @Param("attrIds") List<Long> attrIds);

    /**
     * 删除关联
     *
     * @param attrIds 属性列表
     */
    void deleteByAttrIds(@Param("attrIds") List<Long> attrIds);
}