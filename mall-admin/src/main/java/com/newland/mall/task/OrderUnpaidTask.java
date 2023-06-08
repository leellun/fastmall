package com.newland.mall.task;

import com.newland.mall.entity.Order;
import com.newland.mall.entity.OrderGoods;
import com.newland.mall.enums.OrderStatusEnum;
import com.newland.mall.service.GoodsProductService;
import com.newland.mall.service.OrderGoodsService;
import com.newland.mall.service.OrderService;
import com.newland.mall.utils.BeanUtil;
import com.newland.mall.utils.OrderUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDateTime;
import java.util.List;

public class OrderUnpaidTask extends Task {
    private final Log logger = LogFactory.getLog(OrderUnpaidTask.class);
    private Long orderId = -1L;

    public OrderUnpaidTask(Long orderId, long delayInMilliseconds) {
        super("OrderUnpaidTask-" + orderId, delayInMilliseconds);
        this.orderId = orderId;
    }

    @Override
    public void run() {
        logger.info("系统开始处理延时任务---订单超时未付款---" + this.orderId);

        OrderService orderService = BeanUtil.getBean(OrderService.class);
        OrderGoodsService orderGoodsService = BeanUtil.getBean(OrderGoodsService.class);
        GoodsProductService productService = BeanUtil.getBean(GoodsProductService.class);
//        WxOrderService wxOrderService = BeanUtil.getBean(WxOrderService.class);

        Order order = orderService.getById(this.orderId);
        if (order == null) {
            return;
        }
        if (!order.getOrderStatus().equals(OrderStatusEnum.STATUS_CREATE.getKey())) {
            return;
        }

        // 设置订单已取消状态
        order.setOrderStatus(OrderUtil.STATUS_AUTO_CANCEL);
        order.setEndTime(LocalDateTime.now());
        if (orderService.updateWithOptimisticLocker(order) == 0) {
            throw new RuntimeException("更新数据已失效");
        }

        // 商品货品数量增加
        Long orderId = order.getId();
        List<OrderGoods> orderGoodsList = orderGoodsService.listByOrderId(orderId);
        for (OrderGoods orderGoods : orderGoodsList) {
            Long productId = orderGoods.getProductId();
            Integer number = orderGoods.getNumber();
            if (productService.addStock(productId, number) == 0) {
                throw new RuntimeException("商品货品库存增加失败");
            }
        }

        //返还优惠券
//        wxOrderService.releaseCoupon(orderId);

        logger.info("系统结束处理延时任务---订单超时未付款---" + this.orderId);
    }
}
