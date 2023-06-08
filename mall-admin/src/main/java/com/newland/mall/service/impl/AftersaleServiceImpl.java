package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Aftersale;
import com.newland.mall.entity.Order;
import com.newland.mall.entity.OrderGoods;
import com.newland.mall.enums.AftersaleStatusEnum;
import com.newland.mall.enums.AftersaleTypeEnum;
import com.newland.mall.enums.MallErrorEnum;
import com.newland.mall.enums.OrderStatusEnum;
import com.newland.mall.exception.BusinessException;
import com.newland.mall.mapper.AftersaleMapper;
import com.newland.mall.model.vo.wx.AftersaleVO;
import com.newland.mall.notify.NotifyService;
import com.newland.mall.notify.NotifyType;
import com.newland.mall.service.*;
import com.newland.mall.utils.AssertUtil;
import com.newland.mall.utils.OrderUtil;
import com.newland.mall.utils.OrderUtils;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 售后表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Slf4j
@Service
public class AftersaleServiceImpl extends ServiceImpl<AftersaleMapper, Aftersale> implements AftersaleService {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderGoodsService orderGoodsService;
    @Autowired
    private GoodsProductService goodsProductService;
    @Autowired
    private NotifyService notifyService;
    @Autowired
    private LogHelper logHelper;

    @Override
    public PageInfo<Aftersale> getAftersalePage(Long orderId, String aftersaleSn, Integer status, PageEntity pageEntity) {
        PageInfo<Aftersale> pageInfo = PageWrapper.page(pageEntity, () -> baseMapper.listAftersales(orderId, aftersaleSn, status));
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void recept(Aftersale aftersale) {
        Aftersale aftersaleOne = baseMapper.selectByPrimaryKey(aftersale.getId());
        AssertUtil.notNull(aftersaleOne, "售后不存在");
        Integer status = aftersaleOne.getStatus();
        AssertUtil.isTrue(status.equals(AftersaleStatusEnum.STATUS_REQUEST.getKey()), "售后不能进行审核通过操作");
        aftersaleOne.setStatus(AftersaleStatusEnum.STATUS_RECEPT.getKey());
        aftersaleOne.setHandleTime(LocalDateTime.now());
        baseMapper.updateByPrimaryKeySelective(aftersale);
        // 订单也要更新售后状态
        orderService.updateAftersaleStatus(aftersaleOne.getOrderId(), AftersaleStatusEnum.STATUS_RECEPT.getKey());
    }

    @Override
    public void batchRecept(List<Long> idList) {
        // NOTE
        // 批量操作中，如果一部分数据项失败，应该如何处理
        // 这里采用忽略失败，继续处理其他项。
        // 当然开发者可以采取其他处理方式，具体情况具体分析，例如利用事务回滚所有操作然后返回用户失败信息
        for (Long id : idList) {
            Aftersale aftersale = baseMapper.selectByPrimaryKey(id);
            if (aftersale == null) {
                continue;
            }
            Integer status = aftersale.getStatus();
            if (!status.equals(AftersaleStatusEnum.STATUS_REQUEST.getKey())) {
                continue;
            }
            aftersale.setStatus(AftersaleStatusEnum.STATUS_RECEPT.getKey());
            aftersale.setHandleTime(LocalDateTime.now());
            baseMapper.updateByPrimaryKeySelective(aftersale);

            // 订单也要更新售后状态
            orderService.updateAftersaleStatus(aftersale.getOrderId(), AftersaleStatusEnum.STATUS_RECEPT.getKey());
        }
    }

    @Override
    public void reject(Aftersale aftersale) {
        Aftersale aftersaleOne = baseMapper.selectByPrimaryKey(aftersale.getId());
        AssertUtil.notNull(aftersaleOne, "数据不存在");
        Integer status = aftersaleOne.getStatus();
        AssertUtil.isTrue(status.equals(AftersaleStatusEnum.STATUS_REQUEST.getKey()), "售后不能进行审核拒绝操作");
        aftersaleOne.setStatus(AftersaleStatusEnum.STATUS_REJECT.getKey());
        aftersaleOne.setHandleTime(LocalDateTime.now());
        baseMapper.updateByPrimaryKeySelective(aftersaleOne);

        // 订单也要更新售后状态
        orderService.updateAftersaleStatus(aftersaleOne.getOrderId(), AftersaleStatusEnum.STATUS_REJECT.getKey());
    }

    @Override
    public void batchReject(List<Long> idList) {
        for (Long id : idList) {
            Aftersale aftersale = baseMapper.selectByPrimaryKey(id);
            if (aftersale == null) {
                continue;
            }
            Integer status = aftersale.getStatus();
            if (!status.equals(AftersaleStatusEnum.STATUS_REQUEST.getKey())) {
                continue;
            }
            aftersale.setStatus(AftersaleStatusEnum.STATUS_REJECT.getKey());
            aftersale.setHandleTime(LocalDateTime.now());
            baseMapper.updateByPrimaryKeySelective(aftersale);

            // 订单也要更新售后状态
            orderService.updateAftersaleStatus(aftersale.getOrderId(), AftersaleStatusEnum.STATUS_REJECT.getKey());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refund(Aftersale aftersale) {
//        Aftersale aftersaleOne = baseMapper.selectByPrimaryKey(aftersale.getId());
//        AssertUtil.notNull(aftersaleOne, "数据不存在");
//        AssertUtil.isTrue(aftersaleOne.getStatus().equals(AftersaleStatusEnum.STATUS_RECEPT.getKey()), "售后不能进行退款操作");
//        Long orderId = aftersaleOne.getOrderId();
//        Order order = orderService.getById(orderId);
//
//        // 微信退款
//        WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();
//        wxPayRefundRequest.setOutTradeNo(order.getOrderSn());
//        wxPayRefundRequest.setOutRefundNo("refund_" + order.getOrderSn());
//        // 元转成分
//        Integer totalFee = aftersaleOne.getAmount().multiply(new BigDecimal(100)).intValue();
//        wxPayRefundRequest.setTotalFee(order.getActualPrice().multiply(new BigDecimal(100)).intValue());
//        wxPayRefundRequest.setRefundFee(totalFee);
//
//        WxPayRefundResult wxPayRefundResult;
//        try {
//            wxPayRefundResult = wxPayService.refund(wxPayRefundRequest);
//        } catch (WxPayException e) {
//            log.error(e.getMessage(), e);
//            throw new BusinessException(MallErrorEnum.ORDER_REFUND_FAILED.getKey(), MallErrorEnum.ORDER_REFUND_FAILED.getDesc());
//        }
//        //订单退款失败
//        AssertUtil.isTrue(wxPayRefundResult.getReturnCode().equals("SUCCESS"), MallErrorEnum.ORDER_REFUND_FAILED.getKey(), MallErrorEnum.ORDER_REFUND_FAILED.getDesc());
//
//        aftersaleOne.setStatus(AftersaleStatusEnum.STATUS_REFUND.getKey());
//        aftersaleOne.setHandleTime(LocalDateTime.now());
//        baseMapper.updateByPrimaryKeySelective(aftersaleOne);
//
//        orderService.updateAftersaleStatus(orderId, AftersaleStatusEnum.STATUS_REFUND.getKey());
//
//        // NOTE
//        // 如果是“退货退款”类型的售后，这里退款说明用户的货已经退回，则需要商品货品数量增加
//        // 开发者也可以删除一下代码，在其他地方增加商品货品入库操作
//        if (aftersale.getType().equals(AftersaleTypeEnum.TYPE_GOODS_REQUIRED.getKey())) {
//            List<OrderGoods> orderGoodsList = orderGoodsService.listByOrderId(orderId);
//            for (OrderGoods orderGoods : orderGoodsList) {
//                Long productId = orderGoods.getProductId();
//                Integer number = orderGoods.getNumber();
//                goodsProductService.addStock(productId, number);
//            }
//        }
//
//        // 发送短信通知，这里采用异步发送
//        // 退款成功通知用户, 例如“您申请的订单退款 [ 单号:{1} ] 已成功，请耐心等待到账。”
//        // TODO 注意订单号只发后6位
//        notifyService.notifySmsTemplate(order.getMobile(), NotifyType.REFUND,
//                new String[]{order.getOrderSn().substring(8, 14)});
//
//        logHelper.logOrderSucceed("退款", "订单编号 " + order.getOrderSn() + " 售后编号 " + aftersale.getAftersaleSn());
    }

    @Override
    public PageInfo<AftersaleVO> list(Long userId, Integer status, PageEntity pageEntity) {
        PageInfo<Aftersale> pageinfo = PageWrapper.page(pageEntity, () -> baseMapper.listByUserIdAndStatus(userId, status));
        List<AftersaleVO> aftersaleVOS = new ArrayList<>();
        pageinfo.getList().forEach(item -> {
            List<OrderGoods> orderGoodsList = orderGoodsService.listByOrderId(item.getOrderId());
            AftersaleVO aftersaleVO = new AftersaleVO();
            aftersaleVO.setAftersale(item);
            aftersaleVO.setOrderGoods(orderGoodsList);
            aftersaleVOS.add(aftersaleVO);
        });
        PageInfo<AftersaleVO> result = PageWrapper.newPageInfo(pageinfo, aftersaleVOS);
        return result;
    }

    @Override
    public AftersaleVO getAftersale(Long userId, Long orderId) {
        Aftersale aftersale = baseMapper.getByOrderId(orderId);
        AssertUtil.notNull(aftersale, "售后不存在");
        AssertUtil.isTrue(aftersale.getUserId().equals(userId), "无权操作");
        Order order = orderService.getById(orderId);
        AssertUtil.notNull(order, "订单不存在");
        List<OrderGoods> orderGoodsList = orderGoodsService.listByOrderId(aftersale.getOrderId());
        AftersaleVO aftersaleVO = new AftersaleVO();
        aftersaleVO.setAftersale(aftersale);
        aftersaleVO.setOrderGoods(orderGoodsList);
        aftersaleVO.setOrder(order);
        return aftersaleVO;
    }

    @Override
    public void submit(Long userId, Aftersale aftersale) {
        validate(aftersale);
        AssertUtil.notNull(aftersale.getOrderId(), "订单不存在");
        Order order = orderService.getById(aftersale.getOrderId());
        AssertUtil.notNull(order, "订单不存在");
        // 订单必须完成才能进入售后流程。
        if (!OrderUtil.isConfirmStatus(order) && !OrderUtil.isAutoConfirmStatus(order)) {
            throw new BusinessException("不能申请售后");
        }
        BigDecimal amount = order.getActualPrice().subtract(order.getFreightPrice());
        if (aftersale.getAmount().compareTo(amount) > 0) {
            throw new BusinessException("退款金额不正确");
        }
        Integer afterStatus = order.getAftersaleStatus();
        if (AftersaleStatusEnum.STATUS_RECEPT.getKey().equals(afterStatus) || AftersaleStatusEnum.STATUS_REFUND.getKey().equals(afterStatus)) {
            throw new BusinessException("已申请售后");
        }

        // 如果有旧的售后记录则删除（例如用户已取消，管理员拒绝）
        baseMapper.deleteByOrderId(userId, aftersale.getOrderId());

        aftersale.setStatus(AftersaleStatusEnum.STATUS_REQUEST.getKey());
        aftersale.setAftersaleSn(OrderUtils.generateAftersaleSn(aftersaleSn -> baseMapper.countByAftersaleSn(aftersaleSn)));
        aftersale.setUserId(userId);
        baseMapper.insertSelective(aftersale);

        // 订单的aftersale_status和售后记录的status是一致的。
        orderService.updateAftersaleStatus(aftersale.getOrderId(), AftersaleStatusEnum.STATUS_REQUEST.getKey());
    }

    @Override
    public void cancel(Long userId, Long id) {
        Aftersale aftersaleOne = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(aftersaleOne, "售后不存在");
        AssertUtil.isTrue(userId.equals(aftersaleOne.getUserId()), "无权限操作");
        Long orderId = aftersaleOne.getOrderId();
        Order order = orderService.getById(orderId);
        AssertUtil.isTrue(userId.equals(order.getUserId()), "无权限操作");

        // 订单必须完成才能进入售后流程。
        if (!OrderStatusEnum.STATUS_CONFIRM.getKey().equals(order.getOrderStatus()) && !OrderStatusEnum.STATUS_AUTO_CONFIRM.getKey().equals(order.getOrderStatus())) {
            throw new BusinessException("不支持售后");
        }
        Integer afterStatus = order.getAftersaleStatus();
        AssertUtil.isTrue(afterStatus.equals(AftersaleStatusEnum.STATUS_REQUEST.getKey()), "不能取消售后");
        Aftersale aftersale = new Aftersale();
        aftersale.setUserId(userId);
        aftersale.setOrderId(orderId);
        aftersale.setId(aftersaleOne.getId());
        aftersale.setStatus(AftersaleStatusEnum.STATUS_CANCEL.getKey());
        aftersale.setUserId(userId);
        baseMapper.updateByPrimaryKeySelective(aftersale);

        // 订单的aftersale_status和售后记录的status是一致的。
        orderService.updateAftersaleStatus(orderId, AftersaleStatusEnum.STATUS_CANCEL.getKey());
    }

    @Override
    public Integer deleteByOrderId(Long userId, Long orderId) {
        return baseMapper.deleteByOrderId(userId, orderId);
    }

    /**
     * 验证售后
     *
     * @param aftersale
     */
    private void validate(Aftersale aftersale) {
        AssertUtil.notNull(aftersale.getType(), "售后类型不正确");
        AssertUtil.notNull(aftersale.getAmount(), "退款金额未填写");
        AssertUtil.notNull(aftersale.getReason(), "原因未填写");
    }
}