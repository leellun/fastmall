package com.newland.mall.mapper;

import com.newland.mall.entity.GoodsSpec;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品规格表(销售) Mapper 接口
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Repository
public interface GoodsSpecMapper extends BaseMapper<GoodsSpec> {
    /**
     * 根据商品id 获取规格列表
     * @param goodsId
     * @return
     */
    List<GoodsSpec> listByGoodsId(@Param("goodsId") Long goodsId);
}