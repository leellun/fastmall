package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Order;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.dto.OrderPayDTO;
import com.newland.mall.model.dto.OrderRefundDTO;
import com.newland.mall.model.dto.OrderReplyDTO;
import com.newland.mall.model.dto.OrderShipDTO;
import com.newland.mall.model.vo.OrderDetailVO;
import com.newland.mall.model.vo.OrderVO;
import com.newland.mall.model.vo.wx.UserOrderInfoVO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 订单表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface OrderService extends IService<Order> {
    /**
     * 订单列表
     * @param nickname 昵称
     * @param consignee 收货人名称
     * @param orderSn 订单编号
     * @param start 开始时间
     * @param end 结束时间
     * @param orderStatusArray 订单状态
     * @param pageEntity 分页参数
     */
    PageInfo<OrderVO> list(String nickname, String consignee, String orderSn, LocalDateTime start, LocalDateTime end, List<Integer> orderStatusArray, PageEntity pageEntity);

    /**
     * 修改订单售后状态
     * @param orderId
     * @param aftersaleStatus
     */
    void updateAftersaleStatus(Long orderId, Integer aftersaleStatus);

    /**
     * 通过乐观锁修改订单
     * @param order
     */
    Integer updateWithOptimisticLocker(Order order);

    /**
     * 订单详情
     * @param id
     * @return
     */
    OrderDetailVO getOrderDetail(Long id);

    /**
     * 订单退款
     * 1. 检测当前订单是否能够退款;
     * 2. 微信退款操作;
     * 3. 设置订单退款确认状态；
     * 4. 订单商品库存回库。
     * TODO
     * 虽然接入了微信退款API，但是从安全角度考虑，建议开发者删除这里微信退款代码，采用以下两步走步骤：
     * 1. 管理员登录微信官方支付平台点击退款操作进行退款
     * 2. 管理员登录mall管理后台点击退款操作进行订单状态修改和商品库存回库
     */
    void refund(OrderRefundDTO orderRefundDTO);

    /**
     * 发货
     * 1. 检测当前订单是否能够发货
     * 2. 设置订单发货状态
     */
    void ship(OrderShipDTO orderShipDTO);

    /**
     * 订单收款
     * @param orderPayDTO
     */
    void pay(OrderPayDTO orderPayDTO);

    /**
     * 删除订单
     * 1. 检测当前订单是否能够删除
     * 2. 删除订单
     */
    void delete(Long orderId);

    /**
     * 回复订单商品
     */
    void reply(OrderReplyDTO orderReplyDTO);

    /**
     * 订单统计
     * @return
     */
    Long count();

    /**
     * 用户订单信息
     * @param userId
     * @return
     */
    UserOrderInfoVO getOrderInfo(Long userId);
}