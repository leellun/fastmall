package com.newland.mall.mapper;

import com.newland.mall.entity.User;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 用户列表查询
     * @param username
     * @param mobile
     * @return
     */
     List<User> listByUsernameAndMobile(@Param("username") String username,@Param("mobile") String mobile);

    /**
     * 用户统计
     * @return
     */
    Long count();

    /**
     * 获取用户
     * @param username
     * @return
     */
    User getByUsername(@Param("username") String username);
    /**
     * 获取用户
     * @param mobile
     * @return
     */
    User getByMobile(@Param("mobile") String mobile);

    /**
     * 根据openid获取用户
     * @param openId
     * @return
     */
    User getByOpenId(@Param("openId") String openId);
}