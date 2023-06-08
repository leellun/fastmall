package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.GoodsAttributeCategory;
import com.newland.mall.model.vo.GoodsAttributeCategoryItemVo;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 产品属性分类表 服务类
 * @author leellun
 * @since 2023-06-04 21:50:54
 */
public interface GoodsAttributeCategoryService extends IService<GoodsAttributeCategory> {

    /**
     * 创建属性分类
     */
    void create(String name);

    /**
     * 修改属性分类
     */
    void update(Long id, String name);

    /**
     * 删除属性分类
     */
    void delete(Long id);

    /**
     * 获取属性分类详情
     */
    GoodsAttributeCategory getItem(Long id);

    /**
     * 分页查询属性分类
     */
    PageInfo<GoodsAttributeCategory> getList(Integer pageSize, Integer pageNo);

    /**
     * 获取包含属性的属性分类
     * @return 属性分类
     */
    List<GoodsAttributeCategoryItemVo> getListWithAttr();

    /**
     * 所有
     * @return 属性分类
     */
    List<GoodsAttributeCategory> list();
}