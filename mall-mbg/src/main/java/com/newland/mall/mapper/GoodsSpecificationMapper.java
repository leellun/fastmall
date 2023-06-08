package com.newland.mall.mapper;

import com.newland.mall.entity.GoodsSpecification;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品规格表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface GoodsSpecificationMapper extends BaseMapper<GoodsSpecification> {
    /**
     * 根据商品id 获取规格列表
     * @param goodsId
     * @return
     */
    List<GoodsSpecification> listByGoodsId(@Param("goodsId") Long goodsId);
}