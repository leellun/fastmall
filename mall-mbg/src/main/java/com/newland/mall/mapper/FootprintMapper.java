package com.newland.mall.mapper;

import com.newland.mall.entity.Footprint;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户浏览足迹表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface FootprintMapper extends BaseMapper<Footprint> {
    /**
     * 通过用户id和商品id 查询足迹列表
     * @param userId 用户id
     * @param goodsId 商品id
     * @return
     */
    List<Footprint> listByUserIdAndGoodsId(@Param("userId") Long userId,@Param("goodsId") Long goodsId);
}