package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.GoodsAttr;
import com.newland.mall.entity.GoodsGroup;
import com.newland.mall.model.vo.GoodsAttributeCategoryItemVo;
import com.newland.mall.model.vo.GoodsGroupVo;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 产品属性分类表 服务类
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
public interface GoodsGroupService extends IService<GoodsGroup> {
    /**
     * 创建属性分类
     */
    void create(GoodsGroup goodsGroup);

    /**
     * 修改属性分类
     */
    void update(GoodsGroup goodsGroup);

    /**
     * 删除属性分类
     */
    void delete(Long id);

    /**
     * 获取属性分类详情
     */
    GoodsGroupVo getItem(Long id);

    /**
     * 分页查询属性分类
     */
    PageInfo<GoodsGroup> getList(Integer pageSize, Integer pageNo);

    /**
     * 获取包含属性的属性分类
     * @return 属性分类
     */
    List<GoodsAttributeCategoryItemVo> getListWithAttr();

    /**
     * 所有
     * @return 属性分类
     */
    List<GoodsGroup> list();

    /**
     * 属性分组绑定属性id
     * @param gid 属性分组id
     * @param attrIds 属性id列表
     */
    void bindAttrs(Long gid, List<Long> attrIds);
}