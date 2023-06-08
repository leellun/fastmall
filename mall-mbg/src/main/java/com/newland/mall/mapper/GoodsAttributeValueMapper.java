package com.newland.mall.mapper;

import com.newland.mall.entity.GoodsAttributeValue;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品参数表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface GoodsAttributeValueMapper extends BaseMapper<GoodsAttributeValue> {
    /**
     * 根据goodsId获取参数
     * @param goodsId
     * @return
     */
    List<GoodsAttributeValue> listByGoodsId(@Param("goodsId") Long goodsId);
}