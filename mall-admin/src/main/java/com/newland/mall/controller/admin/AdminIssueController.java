package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Issue;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.IssueService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "通用问题")
@RestController
@RequestMapping("/admin/issue")
@Validated
public class AdminIssueController {
    @Autowired
    private IssueService issueService;

    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<Issue>> list(String question,
                             @RequestParam(defaultValue = "1") Integer pageNo,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @Sort @RequestParam(defaultValue = "create_time") String order,
                             @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity=PageEntity.page(pageNo,pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<Issue> issueList = issueService.listIssuePage(question, pageEntity);
        return RestResponse.ok(issueList);
    }


    @Operation(summary = "添加")
    @PostMapping
    public RestResponse create(@RequestBody Issue issue) {
        issueService.add(issue);
        return RestResponse.ok(issue);
    }

    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public RestResponse<Issue> get(Long id) {
        Issue issue = issueService.get(id);
        return RestResponse.ok(issue);
    }

    @Operation(summary = "编辑")
    @PutMapping
    public RestResponse update(@RequestBody Issue issue) {
        issueService.update(issue);
        return RestResponse.ok(issue);
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public RestResponse delete(@RequestBody Long id) {
        issueService.delete(id);
        return RestResponse.success("操作成功");
    }

}
