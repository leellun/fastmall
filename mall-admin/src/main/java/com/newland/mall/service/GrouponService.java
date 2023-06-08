package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Groupon;
import com.newland.mall.entity.GrouponRules;
import com.newland.mall.model.StatisticsEntity;
import com.newland.mall.model.vo.GrouponListItemVO;
import com.newland.mall.model.vo.wx.GrouponDetailVO;
import com.newland.mall.model.vo.wx.GrouponJoinVO;
import com.newland.mall.model.vo.wx.GrouponRelatedVO;
import com.newland.mall.model.vo.wx.GrouponRuleVO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 团购活动表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface GrouponService extends IService<Groupon> {
    /**
     * 分页获取团购活动
     * @param grouponRuleId
     * @param pageEntity
     * @return
     */
    PageInfo<GrouponListItemVO> listGrouponList(Long grouponRuleId, PageEntity pageEntity);

    /**
     * 根据规则id 获取团购信息
     * @param grouponRuleId
     * @return
     */
    List<Groupon> listByRuleId(Long grouponRuleId);

    /**
     * 团购详情
     * @param userId
     * @param grouponId
     * @return
     */
    GrouponDetailVO getDetail(Long userId, Long grouponId);

    /**
     * 获取参团信息
     * @param grouponId 团购活动id
     * @return
     */
    GrouponJoinVO getGroupJoin(Long grouponId);

    /**
     * 获取指定用户相关团购信息
     * @param userId
     * @param showType
     */
    StatisticsEntity<GrouponRelatedVO> getMyRelatedGroupon(Long userId, Integer showType);

    /**
     * 关联活动订单表
     * @param orderId
     * @return
     */
    Groupon getByOrderId(Long orderId);

    /**
     * 获取参团人数
     * @param grouponId
     * @return
     */
    Long countGroupon(Long grouponId);

    /**
     * 用户是否已经参团
     * @param userId
     * @param grouponId
     * @return
     */
    boolean hasJoin(Long userId, Long grouponId);

    /**
     * 创建团购
     * @param groupon
     */
    void createGroupon(Groupon groupon);

    /**
     * 获取某个团购活动参与的记录
     * @param grouponId
     * @return
     */
    List<Groupon> listJoinRecord(Long grouponId);
}