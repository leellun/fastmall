package com.newland.mall.mapper;

import com.newland.mall.entity.GoodsSaleAttr;
import com.newland.mall.model.vo.GoodsSaleAttrVo;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * 商品规格表(销售) Mapper 接口
 * @author leellun
 * @since 2023-06-11 15:11:54
 */
@Repository
public interface GoodsSaleAttrMapper extends BaseMapper<GoodsSaleAttr> {
    /**
     * 带值销售属性
     * @param goodsId 商品id
     * @return 销售属性
     */
    List<GoodsSaleAttrVo> listWithSpecByGoodsId(@Param("goodsId") Long goodsId);
    /**
     * 通过商品id获取销售属性
     * @param goodsId 商品id
     * @return 销售属性
     */
    List<GoodsSaleAttr> listByGoodsId(@Param("goodsId") Long goodsId);
}