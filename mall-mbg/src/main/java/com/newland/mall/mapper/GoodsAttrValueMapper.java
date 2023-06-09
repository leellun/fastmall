package com.newland.mall.mapper;

import com.newland.mall.entity.GoodsAttrValue;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品参数表 Mapper 接口
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Repository
public interface GoodsAttrValueMapper extends BaseMapper<GoodsAttrValue> {
    /**
     * 根据goodsId获取参数
     * @param goodsId
     * @return
     */
    List<GoodsAttrValue> listByGoodsId(@Param("goodsId") Long goodsId);
}