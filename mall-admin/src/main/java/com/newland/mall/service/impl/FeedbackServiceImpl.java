package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Feedback;
import com.newland.mall.entity.User;
import com.newland.mall.enumeration.BasicEnum;
import com.newland.mall.mapper.FeedbackMapper;
import com.newland.mall.service.FeedbackService;
import com.newland.mall.service.UserService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 意见反馈表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements FeedbackService {
    @Autowired
    private UserService userService;
    @Override
    public PageInfo<Feedback> listFeeks(Integer userId, String username, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listFeeks(userId, username));
    }

    @Override
    public void submit(Long userId, Feedback feedback) {
        Integer hasPicture = feedback.getHasPicture();
        if (hasPicture == null || hasPicture.equals(BasicEnum.YES.getKey())) {
            feedback.setPicUrls(new String[0]);
        }
        User user = userService.getById(userId);
        String username = user.getUsername();
        feedback.setId(null);
        feedback.setUserId(userId);
        feedback.setUsername(username);
        //状态默认是0，1表示状态已发生变化
        feedback.setStatus(BasicEnum.YES.getKey());
        baseMapper.insert(feedback);
    }
}