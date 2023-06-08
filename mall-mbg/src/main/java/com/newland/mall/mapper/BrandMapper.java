package com.newland.mall.mapper;

import com.newland.mall.entity.Brand;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 品牌商表 Mapper 接口
 *
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface BrandMapper extends BaseMapper<Brand> {
    /**
     * 列表查询
     *
     * @param id   品牌id
     * @param name 品牌名称
     * @return
     */
    List<Brand> listBrand(@Param("id") String id, @Param("name") String name);

    /**
     * 所有品牌
     *
     * @return
     */
    List<Brand> listAll();
}