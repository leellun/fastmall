package com.newland.mall.task;

import com.newland.mall.entity.Groupon;
import com.newland.mall.entity.GrouponRules;
import com.newland.mall.entity.Order;
import com.newland.mall.enums.GrouponRulesStatusEnum;
import com.newland.mall.enums.GrouponStatusEnum;
import com.newland.mall.service.GrouponRulesService;
import com.newland.mall.service.GrouponService;
import com.newland.mall.service.OrderService;
import com.newland.mall.utils.BeanUtil;
import com.newland.mall.utils.OrderUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

public class GrouponRuleExpiredTask extends Task {
    private final Log logger = LogFactory.getLog(GrouponRuleExpiredTask.class);
    private Long grouponRuleId = -1L;

    public GrouponRuleExpiredTask(Long grouponRuleId, long delayInMilliseconds){
        super("GrouponRuleExpiredTask-" + grouponRuleId, delayInMilliseconds);
        this.grouponRuleId = grouponRuleId;
    }

    @Override
    public void run() {
        logger.info("系统开始处理延时任务---团购规则过期---" + this.grouponRuleId);

        OrderService orderService = BeanUtil.getBean(OrderService.class);
        GrouponService grouponService = BeanUtil.getBean(GrouponService.class);
        GrouponRulesService grouponRulesService = BeanUtil.getBean(GrouponRulesService.class);

        GrouponRules grouponRules = grouponRulesService.getById(grouponRuleId);
        if(grouponRules == null){
            return;
        }
        if(!grouponRules.getStatus().equals(GrouponRulesStatusEnum.RULE_STATUS_ON.getKey())){
            return;
        }

        // 团购活动取消
        grouponRules.setStatus(GrouponRulesStatusEnum.RULE_STATUS_DOWN_EXPIRE.getKey());
        grouponRulesService.updateById(grouponRules);

        List<Groupon> grouponList = grouponService.listByRuleId(grouponRuleId);
        // 用户团购处理
        for(Groupon groupon : grouponList){
            Integer status = groupon.getStatus();
            Order order = orderService.getById(groupon.getOrderId());
            if(status.equals(GrouponStatusEnum.STATUS_NONE.getKey())){
                groupon.setStatus(GrouponStatusEnum.STATUS_FAIL.getKey());
                grouponService.updateById(groupon);
            }
            else if(status.equals(GrouponStatusEnum.STATUS_ON.getKey())){
                // 如果团购进行中
                // (1) 团购设置团购失败等待退款状态
                groupon.setStatus(GrouponStatusEnum.STATUS_FAIL.getKey());
                grouponService.updateById(groupon);
                // (2) 团购订单申请退款
                if(OrderUtil.isPayStatus(order)) {
                    order.setOrderStatus(OrderUtil.STATUS_REFUND);
                    orderService.updateWithOptimisticLocker(order);
                }
            }
        }
        logger.info("系统结束处理延时任务---团购规则过期---" + this.grouponRuleId);
    }
}
