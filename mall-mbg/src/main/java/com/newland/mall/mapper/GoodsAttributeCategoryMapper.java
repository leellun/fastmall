package com.newland.mall.mapper;

import com.newland.mall.entity.GoodsAttributeCategory;
import com.newland.mall.model.vo.GoodsAttributeCategoryItemVo;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品属性分类表 Mapper 接口
 * @author leellun
 * @since 2023-06-04 21:50:54
 */
@Repository
public interface GoodsAttributeCategoryMapper extends BaseMapper<GoodsAttributeCategory> {

    /**
     * 通过名称获取属性分类
     * @param name
     * @return
     */
    GoodsAttributeCategory getByName(@Param("name") String name);

    /**
     * 列表
     * @return 属性分类列表
     */
    List<GoodsAttributeCategory> list();

    /**
     * 带属性 属性分类
     * @return
     */
    List<GoodsAttributeCategoryItemVo> listWithAttr();
}