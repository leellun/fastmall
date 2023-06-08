package com.newland.mall.mapper;

import com.newland.mall.entity.Feedback;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 意见反馈表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface FeedbackMapper extends BaseMapper<Feedback> {
    /**
     * 反馈列表
     * @param userId 用户id
     * @param username 用户名称
     * @return
     */
    List<Feedback> listFeeks(@Param("userId") Integer userId,@Param("username") String username);
}