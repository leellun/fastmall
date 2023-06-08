package com.newland.mall.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.SearchHistory;
import com.newland.mall.model.vo.wx.SearchInfoVO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

/**
 * 搜索历史表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface SearchHistoryService extends IService<SearchHistory> {
    /**
     * 搜索历史
     * @param userId
     * @param keyword
     * @param pageEntity
     * @return
     */
    PageInfo<SearchHistory> listHistory(Long userId, String keyword, PageEntity pageEntity);

    /**
     * 添加搜索历史
     * @param searchHistoryVo
     */
    void add(SearchHistory searchHistoryVo);

    /**
     * 获取搜索页面信息
     * @param userId
     * @return
     */
    SearchInfoVO getSearchInfo(Long userId);

    /**
     * 删除历史记录
     * @param userId
     */
    void deleteByUid(Long userId);
}