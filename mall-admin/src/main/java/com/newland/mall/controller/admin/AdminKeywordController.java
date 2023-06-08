package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Keyword;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.KeywordService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "关键词")
@RestController
@RequestMapping("/admin/keyword")
@Validated
public class AdminKeywordController {
    @Autowired
    private KeywordService keywordService;

    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<Keyword>> list(String keyword, String url,
                                       @RequestParam(defaultValue = "1") Integer pageNo,
                                       @RequestParam(defaultValue = "10") Integer pageSize,
                                       @Sort @RequestParam(defaultValue = "create_time") String order,
                                       @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity=PageEntity.page(pageNo,pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<Keyword> keywordList = keywordService.listKeywordPage(keyword, url,pageEntity);
        return RestResponse.ok(keywordList);
    }

    @Operation(summary = "添加")
    @PostMapping
    public RestResponse create(@RequestBody Keyword keyword) {
        keywordService.add(keyword);
        return RestResponse.ok(keyword);
    }

    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public RestResponse<Keyword> get(@PathVariable Long id) {
        Keyword keyword = keywordService.get(id);
        return RestResponse.ok(keyword);
    }

    @Operation(summary = "编辑")
    @PutMapping
    public RestResponse update(@RequestBody Keyword keyword) {
        keywordService.update(keyword);
        return RestResponse.ok(keyword);
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public RestResponse delete(@PathVariable Long id) {
        keywordService.delete(id);
        return RestResponse.success("操作成功");
    }

}
