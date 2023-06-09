package com.newland.mall.mapper;

import com.newland.mall.entity.GoodsAttr;
import com.newland.mall.entity.GoodsGroup;
import com.newland.mall.model.vo.GoodsAttributeCategoryItemVo;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品属性分类表 Mapper 接口
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Repository
public interface GoodsGroupMapper extends BaseMapper<GoodsGroup> {

    /**
     * 通过名称获取属性分类
     * @param name
     * @return
     */
    GoodsGroup getByName(@Param("name") String name);

    /**
     * 列表
     * @return 属性分类列表
     */
    List<GoodsGroup> list();

    /**
     * 带属性 属性分类
     * @return
     */
    List<GoodsAttributeCategoryItemVo> listWithAttr();
    /**
     * 商品属性
     *
     * @param gid  商品分组
     * @param type 类型
     * @return 属性列表
     */
    List<GoodsAttr> listByGidAndType(@Param("gid") Long gid, @Param("type") Integer type);
}