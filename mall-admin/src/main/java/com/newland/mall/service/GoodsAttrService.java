package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.GoodsAttr;
import com.newland.mall.model.dto.GoodsAttributeDto;
import com.newland.mall.model.vo.GoodsAttrInfoVo;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 商品属性参数表 服务类
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
public interface GoodsAttrService extends IService<GoodsAttr> {
    /**
     * 根据分类分页获取商品属性
     * @param type 0->属性；2->参数
     */
    PageInfo<GoodsAttr> getList(Integer type, Integer pageSize, Integer pageNo);

    /**
     * 获取属性分组下所有属性，包含规格属性和销售属性
     * @param gid
     * @return
     */
    List<GoodsAttr> getListAttr(Long gid, Integer type);
    /**
     * 添加商品属性
     */
    void create(GoodsAttributeDto productAttributeDto);

    /**
     * 修改商品属性
     */
    void update(Long id, GoodsAttributeDto productAttributeDto);

    /**
     * 获取单个商品属性信息
     */
    GoodsAttr getItem(Long id);

    /**
     * 删除属性
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 获取产品属性
     * @param goodsCategoryId
     * @return
     */
    List<GoodsAttrInfoVo> getGoodsAttrInfo(Long goodsCategoryId);
}