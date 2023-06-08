package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.User;
import com.newland.mall.model.dto.wx.UserProfileDTO;
import com.newland.mall.model.dto.wx.WxBindPhoneDTO;
import com.newland.mall.model.vo.UserVO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

/**
 * 用户表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface UserService extends IService<User> {
    /**
     * 通过用户id获取用信息
     * @param userId
     * @return
     */
    UserVO getUserVoByUserId(Long userId);

    /**
     * 列表
     * @param username
     * @param mobile
     * @param pageEntity
     * @return
     */
    PageInfo<User> listUserPage(String username, String mobile, PageEntity pageEntity);

    /**
     * 获取用户
     * @param id
     * @return
     */
    User get(Long id);

    /**
     * 更新
     * @param user
     */
    void add(User user);
    /**
     * 更新
     * @param user
     */
    void update(User user);

    /**
     * 用户数
     * @return
     */
    Long count();

    /**
     * 通过openId获取用户
     * @param openId
     * @return
     */
    User getByOid(String openId);

    /**
     * 更新用户信息
     * @param userId
     * @param userProfileDTO
     */
    void updateProfile(Long userId, UserProfileDTO userProfileDTO);

    /**
     * 个人信息
     * @param userId
     * @return
     */
    User getInfo(Long userId);
}