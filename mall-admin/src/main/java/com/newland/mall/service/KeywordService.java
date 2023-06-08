package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Keyword;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 关键字表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface KeywordService extends IService<Keyword> {
    /**
     * 分页获取关键词
     * @param keyword
     * @param url
     * @param pageEntity
     * @return
     */
    PageInfo<Keyword> listKeywordPage(String keyword, String url, PageEntity pageEntity);

    /**
     * 详情
     * @param id
     * @return
     */
    Keyword get(Long id);

    /**
     * 添加
     * @param keyword
     */

    void add(Keyword keyword);

    /**
     * 更新
     * @param keyword
     */

    void update(Keyword keyword);

    /**
     * 删除
     * @param id
     */

    void delete(Long id);

    /**
     * 取出输入框默认的关键词
     * @return
     */
    Keyword getDefault();

    /**
     * hot关键词
     * @return
     */
    List<Keyword> listHots();
}