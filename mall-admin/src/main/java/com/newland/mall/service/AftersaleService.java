package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Aftersale;
import com.newland.mall.model.vo.wx.AftersaleVO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 售后表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface AftersaleService extends IService<Aftersale> {
    /**
     * 列表
     * @param orderId 订单id
     * @param aftersaleSn 售后编号
     * @param status 状态
     * @param pageEntity 分页
     * @return
     */
    PageInfo<Aftersale> getAftersalePage(Long orderId, String aftersaleSn, Integer status, PageEntity pageEntity);

    /**
     * 审核通过
     * @param aftersale
     */
    void recept(Aftersale aftersale);

    /**
     * 批量通过
     * @param idList
     */
    void batchRecept(List<Long> idList);

    /**
     * 审核拒绝
     * @param aftersale
     */
    void reject(Aftersale aftersale);

    /**
     * 批量拒绝
     * @param idList
     */
    void batchReject(List<Long> idList);

    /**
     * 退款
     * @param aftersale
     */
    void refund(Aftersale aftersale);

    /**
     * 分页获取 售后信息
     * @param userId 用户id
     * @param status 状态
     * @param pageEntity 分页
     * @return
     */
    PageInfo<AftersaleVO> list(Long userId, Integer status, PageEntity pageEntity);

    /**
     * 获取售后详情
     * @param userId
     * @param orderId
     * @return
     */
    AftersaleVO getAftersale(Long userId, Long orderId);

    /**
     * 提交售后
     * @param userId 用户id
     * @param aftersale 售后
     */
    void submit(Long userId, Aftersale aftersale);

    /**
     * 取消售后
     * @param userId
     * @param aftersale
     */
    void cancel(Long userId, Long aftersale);

    /**
     * 删除用户对应售后
     * @param userId
     * @param orderId
     */
    Integer deleteByOrderId(Long userId, Long orderId);
}