package com.newland.mall.mapper;

import com.newland.mall.entity.Goods;
import com.newland.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 商品基本信息表 Mapper 接口
 * @author leellun
 * @since 2023-06-13 00:00:11
 */
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {
}