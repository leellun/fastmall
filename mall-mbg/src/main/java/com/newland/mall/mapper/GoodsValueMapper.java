package com.newland.mall.mapper;

import com.newland.mall.entity.GoodsValue;
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
public interface GoodsValueMapper extends BaseMapper<GoodsValue> {
    /**
     * 属性值
     * @param goodsAttrId 属性id
     * @return 列表
     */
    List<GoodsValue> listByAttrId(@Param("goodsAttrId") Long goodsAttrId);
}