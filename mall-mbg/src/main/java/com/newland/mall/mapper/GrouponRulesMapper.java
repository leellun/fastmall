package com.newland.mall.mapper;

import com.newland.mall.entity.GrouponRules;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 团购规则表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface GrouponRulesMapper extends BaseMapper<GrouponRules> {
    /**
     * 根据商品id获取规则
     * @param goodsId
     * @return
     */
    List<GrouponRules> listByGoodsId(@Param("goodsId") Long goodsId);

    /**
     * 指定商品是否在团购中
     * @param goodsId
     * @return
     */
    Integer countByGoodsId(@Param("goodsId") Long goodsId);

    /**
     * 指定状态 规则
     * @param status
     * @return
     */
    List<GrouponRules> listByStatus(@Param("status") Integer status);
    /**
     * 规则列表
     * @return
     */
    List<GrouponRules> list();

}