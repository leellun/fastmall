package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Issue;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

/**
 * 常见问题表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface IssueService extends IService<Issue> {
    /**
     * 分页获取问题表
     * @param question
     * @param pageEntity
     * @return
     */
    PageInfo<Issue> listIssuePage(String question, PageEntity pageEntity);

    void delete(Long id);

    Issue get(Long id);

    void add(Issue issue);

    void update(Issue issue);

}