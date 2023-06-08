package com.newland.mall.mapper;

import com.newland.mall.entity.Issue;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 常见问题表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface IssueMapper extends BaseMapper<Issue> {
    /**
     * 搜索问题
     * @param question
     * @return
     */
    List<Issue> listByQuestion(@Param("question") String question);
}