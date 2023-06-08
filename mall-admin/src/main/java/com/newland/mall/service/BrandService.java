package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Brand;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 品牌商表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface BrandService extends IService<Brand> {
    /**
     * 列表查询
     * @param id id
     * @param name 名称
     * @param pageEntity 分页参数
     * @return
     */
    PageInfo<Brand> listPage(String id, String name, PageEntity pageEntity);

    /**
     * 添加品牌
     * @param brand
     */
    void add(Brand brand);

    /**
     * 获取品牌
     * @param id
     * @return
     */

    Brand get(Long id);

    /**
     * 更新品牌
     * @param brand
     */
    void update(Brand brand);

    /**
     * 删除品牌
     * @param id
     */
    void delete(Long id);

    /**
     * 所有品牌
     * @return
     */
    List<Brand> all();

    /**
     * 品牌
     * @param pageSize
     * @return
     */
    List<Brand> query(Integer pageSize);
}