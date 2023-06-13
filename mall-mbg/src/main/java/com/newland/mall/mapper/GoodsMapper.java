package com.newland.mall.mapper;

import com.newland.mall.entity.Goods;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品基本信息表 Mapper 接口
 *
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 根据商品名称和销售状态获取商品
     *
     * @param name
     * @return
     */
    Goods getByNameAndSale(@Param("name") String name, @Param("isOnSale") int isOnSale);

    /**
     * 商品基本信息列表获取
     *
     * @param goodsId 商品id
     * @param name
     * @return
     */
    List<Goods> listGoods(@Param("goodsId") Integer goodsId, @Param("name") String name);

    /**
     * 商品列表
     *
     * @param goodsIds
     * @param isOnSale
     * @return
     */
    List<Goods> listByGoodsIdsAndOnSale(@Param("goodsIds") Long[] goodsIds, @Param("isOnSale") Integer isOnSale);

    /**
     * 商品数量
     *
     * @return
     */
    Long count();

    /**
     * 商品列表
     *
     * @param categoryId
     * @param brandId
     * @param keyword
     * @param isHot
     * @param isNew
     * @return
     */
    List<Goods> listSelective(@Param("categoryId") Integer categoryId, @Param("brandId") Integer brandId, @Param("keyword") String keyword, @Param("isHot") Integer isHot, @Param("isNew") Integer isNew);

    /**
     * 查找在售商品
     *
     * @param categoryId 分类
     * @return
     */
    List<Goods> listByCategory(@Param("categoryId") Long categoryId);

    /**
     * 热品
     *
     * @return
     */
    List<Goods> listByHot();

    /**
     * 新品
     *
     * @return
     */
    List<Goods> listByNew();

    /**
     * 分类下的商品
     *
     * @param categoryIds
     * @return
     */
    List<Goods> listByCategorys(@Param("categoryIds") List<Long> categoryIds);

    /**
     * 在售商品
     *
     * @param id
     * @return
     */
    Goods getOnSaleGoods(@Param("id") Long id);
}