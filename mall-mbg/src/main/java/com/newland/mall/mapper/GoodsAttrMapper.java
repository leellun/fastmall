package com.newland.mall.mapper;

import com.newland.mall.entity.GoodsAttr;
import com.newland.mall.model.vo.GoodsAttrInfoVo;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品属性参数表 Mapper 接口
 *
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Repository
public interface GoodsAttrMapper extends BaseMapper<GoodsAttr> {
    /**
     * 批量删除
     *
     * @param ids id list
     * @return count
     */
    int deleteBatchIds(@Param("ids") List<Long> ids);

    /**
     * 属性
     *
     * @param goodsCategoryId 商品分类
     * @return 属性集合
     */
    List<GoodsAttrInfoVo> getGoodsAttrInfo(@Param("goodsCategoryId") Long goodsCategoryId);

    /**
     * 商品属性
     *
     * @param type 类型
     * @return 属性列表
     */
    List<GoodsAttr> listByType(@Param("type") Integer type);

    /**
     * 商品属性
     *
     * @param cid  商品分类
     * @param type 类型
     * @return 属性列表
     */
    List<GoodsAttr> listByCidAndType(@Param("cid") Long cid, @Param("type") Integer type);
}