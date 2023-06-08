package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Keyword;
import com.newland.mall.entity.SearchHistory;
import com.newland.mall.mapper.SearchHistoryMapper;
import com.newland.mall.model.vo.wx.SearchInfoVO;
import com.newland.mall.service.KeywordService;
import com.newland.mall.service.SearchHistoryService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 搜索历史表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class SearchHistoryServiceImpl extends ServiceImpl<SearchHistoryMapper, SearchHistory> implements SearchHistoryService {
    @Autowired
    private KeywordService keywordsService;

    @Override
    public PageInfo<SearchHistory> listHistory(Long userId, String keyword, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listByUserIdAndKeyword(userId, keyword));
    }

    @Override
    public void add(SearchHistory searchHistoryVo) {
        baseMapper.insert(searchHistoryVo);
    }

    @Override
    public SearchInfoVO getSearchInfo(Long userId) {
        //取出输入框默认的关键词
        Keyword defaultKeyword = keywordsService.getDefault();
        //取出热闹关键词
        List<Keyword> hotKeywordList = keywordsService.listHots();

        List<SearchHistory> historyList = null;
        if (userId != null) {
            //取出用户历史关键字
            historyList = baseMapper.listByUserIdAndKeyword(userId, null);
        }
        SearchInfoVO vo = new SearchInfoVO();
        vo.setDefaultKeyword(defaultKeyword);
        vo.setHistoryKeywordList(historyList);
        vo.setHotKeywordList(hotKeywordList);
        return vo;
    }

    @Override
    public void deleteByUid(Long userId) {
        baseMapper.deleteByUserId(userId);
    }
}