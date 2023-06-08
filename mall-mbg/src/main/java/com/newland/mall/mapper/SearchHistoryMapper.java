package com.newland.mall.mapper;

import com.newland.mall.entity.SearchHistory;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 搜索历史表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface SearchHistoryMapper extends BaseMapper<SearchHistory> {
    /**
     * 根据用户id和关键词获取历史信息
     * @param userId 用户id
     * @param keyword 关键词
     * @return
     */
    List<SearchHistory> listByUserIdAndKeyword(@Param("userId") Long userId,@Param("keyword") String keyword);

    /**
     * 删除用户历史记录
     * @param userId
     */
    void deleteByUserId(@Param("userId") Long userId);
}