package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.GoodsValue;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 商品参数表 服务类
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
public interface GoodsValueService extends IService<GoodsValue> {
    /**
     * 列表
     * @param goodsAttrId 属性id
     * @param pageSize 大小
     * @param pageNo 页码
     * @return
     */
    PageInfo<GoodsValue> getList(Long goodsAttrId,  Integer pageNo,Integer pageSize);

    /**
     * 添加
     * @param goodsValue 商品值
     */
    void create(GoodsValue goodsValue);

    /**
     * 更新
     * @param goodsValue 属性值
     */
    void update(GoodsValue goodsValue);

    /**
     * 删除
     * @param ids id 列表
     */
    void delete(List<Long> ids);
}