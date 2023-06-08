package com.newland.mall.mapper;

import com.newland.mall.entity.Order;
import com.newland.mall.model.vo.OrderVO;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 更新 通过比较最后修改时间
     * @param lastUpdateTime
     * @param order
     * @return
     */
    int updateWithOptimisticLocker(@Param("lastUpdateTime") LocalDateTime lastUpdateTime, @Param("order") Order order);

    /**
     * 订单列表
     * @param nickname 昵称
     * @param consignee 收货人名称
     * @param orderSn 订单编号
     * @param start 开始时间
     * @param end 结束时间
     * @param orderStatusArray 订单状态
     * @return
     */
    List<OrderVO> listOrders(@Param("nickname") String nickname,@Param("consignee")  String consignee,@Param("orderSn")  String orderSn,@Param("start")  LocalDateTime start,@Param("end")  LocalDateTime end,@Param("orderStatusArray")  List<Integer> orderStatusArray);

    /**
     * 统计订单数
     * @return
     */
    Long count();

    /**
     * 用户订单
     * @param userId 用户id
     * @param orderStatus 订单状态
     * @return
     */
    List<Order> listByUserIdAndStatus(@Param("userId") Long userId,@Param("orderStatus") List<Integer> orderStatus);

    /**
     * 通过用户id和订单id查询订单
     * @param userId
     * @param orderId
     * @return
     */
    Order getByUserIdAndOrderId(@Param("userId") Long userId,@Param("orderId") Long orderId);

    /**
     * 查看订单号订单是否存在
     * @param orderSn
     * @return
     */
    Long countByOrdersn(@Param("orderSn")  String orderSn);
    /**
     * 查看订单号
     * @param orderSn
     * @return
     */
    Order getByOrdersn(@Param("orderSn")  String orderSn);

    /**
     * 订单信息
     * @param userId
     * @return
     */
    List<Order> listOrderInfo(@Param("userId")Long userId);
}