package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Feedback;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

/**
 * 意见反馈表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface FeedbackService extends IService<Feedback> {
    /**
     * 反馈列表
     * @param userId 用户id
     * @param username 用户名称
     * @param pageEntity 分页
     * @return
     */
    PageInfo<Feedback> listFeeks(Integer userId, String username, PageEntity pageEntity);

    /**
     * 提交意见反馈
     * @param userId
     * @param feedback
     */
    void submit(Long userId, Feedback feedback);
}