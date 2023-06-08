package com.newland.mall.controller.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.SearchHistory;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.SearchHistoryService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leell
 */
@Tag(name = "搜索历史")
@RestController
@RequestMapping("/admin/history")
public class AdminHistoryController {
    @Autowired
    private SearchHistoryService searchHistoryService;

    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<SearchHistory>> list(Long userId, String keyword,
                       @RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @Sort @RequestParam(defaultValue = "create_time") String order,
                       @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity=PageEntity.page(pageNo,pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<SearchHistory> historyList = searchHistoryService.listHistory(userId, keyword, pageEntity);
        return RestResponse.ok(historyList);
    }
}
