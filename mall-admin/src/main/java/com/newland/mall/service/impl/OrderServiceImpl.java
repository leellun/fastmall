package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Comment;
import com.newland.mall.entity.CouponUser;
import com.newland.mall.entity.Order;
import com.newland.mall.entity.OrderGoods;
import com.newland.mall.enums.CouponUserStatusEnum;
import com.newland.mall.exception.BusinessException;
import com.newland.mall.mapper.OrderMapper;
import com.newland.mall.model.dto.OrderPayDTO;
import com.newland.mall.model.dto.OrderRefundDTO;
import com.newland.mall.model.dto.OrderReplyDTO;
import com.newland.mall.model.dto.OrderShipDTO;
import com.newland.mall.model.vo.OrderDetailVO;
import com.newland.mall.model.vo.OrderVO;
import com.newland.mall.model.vo.UserVO;
import com.newland.mall.model.vo.wx.UserOrderInfoVO;
import com.newland.mall.notify.NotifyService;
import com.newland.mall.notify.NotifyType;
import com.newland.mall.service.*;
import com.newland.mall.utils.AssertUtil;
import com.newland.mall.utils.OrderUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.newland.mall.enums.MallErrorEnum.ORDER_REFUND_FAILED;

/**
 * 订单表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private OrderGoodsService orderGoodsService;
    @Autowired
    private UserService userService;
    @Autowired
    private CouponUserService couponUserService;
    @Autowired
    private GoodsProductService goodsProductService;
    @Autowired
    private NotifyService notifyService;
    @Autowired
    private LogHelper logHelper;
    @Autowired
    private CommentService commentService;

    @Override
    public void updateAftersaleStatus(Long orderId, Integer aftersaleStatus) {
        Order dbOrder = baseMapper.selectByPrimaryKey(orderId);
        AssertUtil.notNull(dbOrder, "订单不存在");
        Order order = new Order();
        order.setId(orderId);
        order.setAftersaleStatus(aftersaleStatus);
        order.setUpdateTime(LocalDateTime.now());
        baseMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Integer updateWithOptimisticLocker(Order order) {
        return baseMapper.updateWithOptimisticLocker(order.getUpdateTime(), order);
    }

    @Override
    public PageInfo<OrderVO> list(String nickname, String consignee, String orderSn, LocalDateTime start, LocalDateTime end, List<Integer> orderStatusArray, PageEntity pageEntity) {
        PageInfo<OrderVO> pageInfo = PageWrapper.page(pageEntity, () -> baseMapper.listOrders(nickname, consignee, orderSn, start, end, orderStatusArray));
        return pageInfo;
    }

    @Override
    public OrderDetailVO getOrderDetail(Long id) {
        Order order = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(order, "数据不存在");
        List<OrderGoods> orderGoods = orderGoodsService.listByOrderId(id);
        UserVO user = userService.getUserVoByUserId(order.getUserId());
        OrderDetailVO vo = new OrderDetailVO();
        vo.setOrder(order);
        vo.setOrderGoods(orderGoods);
        vo.setUser(user);
        return vo;
    }

    @Override
    public void refund(OrderRefundDTO orderRefundDTO) {
//        Long orderId = orderRefundDTO.getOrderId();
//        BigDecimal refundMoney = orderRefundDTO.getRefundMoney();
//        AssertUtil.notNull(orderId, "数据异常");
//        AssertUtil.notNull(refundMoney, "数据异常");
//
//        Order order = baseMapper.selectByPrimaryKey(orderId);
//        AssertUtil.notNull(order, "订单不存在");
//
//        AssertUtil.isNotTrue(order.getActualPrice().compareTo(refundMoney) != 0, "操作异常");
//
//        // 如果订单不是退款状态，则不能退款
//        AssertUtil.isNotTrue(!order.getOrderStatus().equals(OrderUtil.STATUS_REFUND), "订单不能确认收货");
//
//        // 微信退款
//        WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();
//        wxPayRefundRequest.setOutTradeNo(order.getOrderSn());
//        wxPayRefundRequest.setOutRefundNo("refund_" + order.getOrderSn());
//        // 元转成分
//        Integer totalFee = order.getActualPrice().multiply(new BigDecimal(100)).intValue();
//        wxPayRefundRequest.setTotalFee(totalFee);
//        wxPayRefundRequest.setRefundFee(totalFee);
//
//        WxPayRefundResult wxPayRefundResult;
//        try {
//            wxPayRefundResult = wxPayService.refund(wxPayRefundRequest);
//        } catch (WxPayException e) {
//            throw new BusinessException(ORDER_REFUND_FAILED.getKey(), "订单退款失败");
//        }
//        if (!wxPayRefundResult.getReturnCode().equals("SUCCESS")) {
//            throw new BusinessException(ORDER_REFUND_FAILED.getKey(), "订单退款失败");
//        }
//        if (!wxPayRefundResult.getResultCode().equals("SUCCESS")) {
//            throw new BusinessException(ORDER_REFUND_FAILED.getKey(), "订单退款失败");
//        }
//
//        LocalDateTime now = LocalDateTime.now();
//        // 设置订单取消状态
//        order.setOrderStatus(OrderUtil.STATUS_REFUND_CONFIRM);
//        order.setEndTime(now);
//        // 记录订单退款相关信息
//        order.setRefundAmount(order.getActualPrice());
//        order.setRefundType("微信退款接口");
//        order.setRefundContent(wxPayRefundResult.getRefundId());
//        order.setRefundTime(now);
//        if (baseMapper.updateWithOptimisticLocker(order.getUpdateTime(), order) == 0) {
//            throw new RuntimeException("更新数据已失效");
//        }
//
//        // 商品货品数量增加
//        List<OrderGoods> orderGoodsList = orderGoodsService.listByOrderId(orderId);
//        for (OrderGoods orderGoods : orderGoodsList) {
//            Long productId = orderGoods.getProductId();
//            Integer number = orderGoods.getNumber();
//            if (goodsProductService.addStock(productId, number) == 0) {
//                throw new RuntimeException("商品货品库存增加失败");
//            }
//        }
//
//        // 返还优惠券
//        List<CouponUser> couponUsers = couponUserService.listByOrderId(orderId);
//        for (CouponUser couponUser : couponUsers) {
//            // 优惠券状态设置为可使用
//            couponUser.setStatus(CouponUserStatusEnum.STATUS_USABLE.getKey());
//            couponUser.setUpdateTime(LocalDateTime.now());
//            couponUserService.updateById(couponUser);
//        }
//
//        //TODO 发送邮件和短信通知，这里采用异步发送
//        // 退款成功通知用户, 例如“您申请的订单退款 [ 单号:{1} ] 已成功，请耐心等待到账。”
//        // 注意订单号只发后6位
//        notifyService.notifySmsTemplate(order.getMobile(), NotifyType.REFUND,
//                new String[]{order.getOrderSn().substring(8, 14)});
//
//        logHelper.logOrderSucceed("退款", "订单编号 " + order.getOrderSn());
    }

    @Override
    public void ship(OrderShipDTO orderShipDTO) {
        Long orderId = orderShipDTO.getOrderId();
        String shipSn = orderShipDTO.getShipSn();
        String shipChannel = orderShipDTO.getShipChannel();
        if (orderId == null || shipSn == null || shipChannel == null) {
            throw new BusinessException("数据异常");
        }

        Order order = baseMapper.selectByPrimaryKey(orderId);
        AssertUtil.notNull(order, "订单不存在");

        // 如果订单不是已付款状态，则不能发货
        AssertUtil.isTrue(order.getOrderStatus().equals(OrderUtil.STATUS_PAY), "订单不能确认收货");

        order.setOrderStatus(OrderUtil.STATUS_SHIP);
        order.setShipSn(shipSn);
        order.setShipChannel(shipChannel);
        order.setShipTime(LocalDateTime.now());
        if (baseMapper.updateWithOptimisticLocker(order.getUpdateTime(), order) == 0) {
            throw new BusinessException("更新数据已经失效");
        }

        //TODO 发送邮件和短信通知，这里采用异步发送
        // 发货会发送通知短信给用户:          *
        // "您的订单已经发货，快递公司 {1}，快递单 {2} ，请注意查收"
        notifyService.notifySmsTemplate(order.getMobile(), NotifyType.SHIP, new String[]{shipChannel, shipSn});

        logHelper.logOrderSucceed("发货", "订单编号 " + order.getOrderSn());
    }

    @Override
    public void pay(OrderPayDTO orderPayDTO) {
        Long orderId = orderPayDTO.getOrderId();
        BigDecimal newMoney = orderPayDTO.getNewMoney();

        if (orderId == null || newMoney == null) {
            throw new BusinessException("参数异常");
        }
        BigDecimal actualPrice = newMoney;

        Order order = baseMapper.selectByPrimaryKey(orderId);
        AssertUtil.notNull(order, "订单不存在");

        AssertUtil.isTrue(order.getOrderStatus().equals(OrderUtil.STATUS_CREATE), "当前订单状态不支持线下收款");

        order.setActualPrice(actualPrice);
        order.setOrderStatus(OrderUtil.STATUS_PAY);
        if (baseMapper.updateWithOptimisticLocker(order.getUpdateTime(), order) == 0) {
            throw new BusinessException("更新数据已失效");
        }
    }

    @Override
    public void reply(OrderReplyDTO orderReplyDTO) {
        Long commentId = orderReplyDTO.getCommentId();
        if (commentId == null || commentId == 0) {
            throw new BusinessException("参数异常");
        }
        // 目前只支持回复一次
        Comment comment = commentService.getById(commentId);
        AssertUtil.notNull(comment, "数据不存在");
        AssertUtil.isTrue(StringUtils.isEmpty(comment.getAdminContent()), "订单商品已回复");
        String content = orderReplyDTO.getContent();
        AssertUtil.isNotTrue(StringUtils.isEmpty(content), "评价内容不能为空");
        // 更新评价回复
        comment.setAdminContent(content);
        commentService.updateById(comment);
    }

    @Override
    public Long count() {
        return baseMapper.count();
    }

    @Override
    public void delete(Long orderId) {
        Order dbOrder = baseMapper.selectByPrimaryKey(orderId);
        AssertUtil.notNull(dbOrder, "订单不存在");

        // 如果订单不是关闭状态(已取消、系统取消、已退款、用户已确认、系统已确认)，则不能删除
        Integer status = dbOrder.getOrderStatus();
        if (!status.equals(OrderUtil.STATUS_CANCEL) && !status.equals(OrderUtil.STATUS_AUTO_CANCEL) &&
                !status.equals(OrderUtil.STATUS_CONFIRM) && !status.equals(OrderUtil.STATUS_AUTO_CONFIRM) &&
                !status.equals(OrderUtil.STATUS_REFUND_CONFIRM)) {
            throw new BusinessException("订单不能删除");
        }
        // 删除订单
        Order order = new Order();
        order.setId(orderId);
        order.setDeleted(1);
        baseMapper.updateByPrimaryKeySelective(order);
        // 删除订单商品
        orderGoodsService.deleteByOrderId(orderId);
        logHelper.logOrderSucceed("删除", "订单编号 " + order.getOrderSn());
    }

    @Override
    public UserOrderInfoVO getOrderInfo(Long userId) {
        List<Order> orders = baseMapper.listOrderInfo(userId);
        int unpaid = 0;
        int unship = 0;
        int unrecv = 0;
        int uncomment = 0;
        for (Order order : orders) {
            if (OrderUtil.isCreateStatus(order)) {
                unpaid++;
            } else if (OrderUtil.isPayStatus(order)) {
                unship++;
            } else if (OrderUtil.isShipStatus(order)) {
                unrecv++;
            } else if (OrderUtil.isConfirmStatus(order) || OrderUtil.isAutoConfirmStatus(order)) {
                uncomment += order.getComments();
            } else {
                // do nothing
            }
        }
        UserOrderInfoVO userOrderInfoVO = new UserOrderInfoVO();
        userOrderInfoVO.setUncomment(uncomment);
        userOrderInfoVO.setUnpaid(unpaid);
        userOrderInfoVO.setUnship(unship);
        userOrderInfoVO.setUnrecv(unrecv);
        return userOrderInfoVO;
    }
}