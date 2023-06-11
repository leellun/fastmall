package com.newland.mall.mapper;

import com.newland.mall.entity.GoodsProduct;
import com.newland.mall.model.vo.GoodsProductVo;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品货品表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface GoodsProductMapper extends BaseMapper<GoodsProduct> {
    /**
     * 更新库存
     * @param id 商品id
     * @param number 库存更新
     */
    Integer updateStock(@Param("id") Long id,@Param("number") Integer number);

    /**
     *
     * @param goodsId
     * @return
     */
    List<GoodsProduct> listByGoodsId(@Param("goodsId") Long goodsId);
    /**
     *
     * @param goodsId
     * @return
     */
    List<GoodsProductVo> listWithSpecByGoodsId(@Param("goodsId") Long goodsId);

    /**
     * 货品数
     * @return
     */
    Long count();

    /**
     * 减少库存
     * @param id
     * @param num
     * @return
     */
    int reduceStock(@Param("id") Long id,@Param("number") Integer num);

    /**
     * 删除
     * @param productIds product id列表
     * @return 数量
     */
    Integer deleteByIds(@Param("productIds") List<Long> productIds);
}