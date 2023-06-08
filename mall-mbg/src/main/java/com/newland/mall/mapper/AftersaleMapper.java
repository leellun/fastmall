package com.newland.mall.mapper;

import com.newland.mall.entity.Aftersale;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 售后表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface AftersaleMapper extends BaseMapper<Aftersale> {
    /**
     * 售后订单
     * @param orderId 订单id
     * @param aftersaleSn 订单编号
     * @param status 订单状态
     * @return
     */
    List<Aftersale> listAftersales(@Param("orderId") Long orderId,@Param("aftersaleSn") String aftersaleSn,@Param("status") Integer status);

    /**
     * 通过用户id和状态获取售后
     * @param userId
     * @param status
     * @return
     */
    List<Aftersale> listByUserIdAndStatus(@Param("userId") Long userId,@Param("status") Integer status);

    /**
     * 通过订单id获取售后
     * @param orderId
     * @return
     */
    Aftersale getByOrderId(@Param("orderId") Long orderId);

    /**
     * 通过用户id和订单id删除售后
     * @param userId
     * @param orderId
     */
    Integer deleteByOrderId(@Param("userId") Long userId,@Param("orderId") Long orderId);

    /**
     * 获取count
     * @param aftersaleSn
     * @return
     */
    Long countByAftersaleSn(@Param("aftersaleSn") String aftersaleSn);
}