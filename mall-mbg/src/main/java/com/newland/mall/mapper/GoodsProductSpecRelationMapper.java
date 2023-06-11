package com.newland.mall.mapper;

import com.newland.mall.entity.GoodsProductSpecRelation;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品货品表-规格 中间表 Mapper 接口
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Repository
public interface GoodsProductSpecRelationMapper extends BaseMapper<GoodsProductSpecRelation> {
    /**
     * 删除通过产品id列表
     * @param productIds 产品id列表
     * @return 数量
     */
    Integer deleteByProductIds(@Param("productIds") List<Long> productIds);
}