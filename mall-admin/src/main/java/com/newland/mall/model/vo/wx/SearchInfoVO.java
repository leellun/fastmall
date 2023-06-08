package com.newland.mall.model.vo.wx;

import com.newland.mall.entity.Keyword;
import com.newland.mall.entity.SearchHistory;
import lombok.Data;

import java.util.List;

/**
 * 搜索页面信息
 * Author: leell
 * Date: 2023/4/6 19:58:51
 */
@Data
public class SearchInfoVO {
    /**
     * 取出输入框默认的关键词
     */
    private Keyword defaultKeyword;
    /**
     * 取出用户历史关键字
     */
    private List<SearchHistory> historyKeywordList;
    /**
     * 取出热闹关键词
     */
    private List<Keyword> hotKeywordList;

}
