package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Topic;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.vo.TopicVO;
import com.newland.mall.service.TopicService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "专题管理")
@RestController
@RequestMapping("/admin/topic")
@Validated
public class AdminTopicController {
    @Autowired
    private TopicService topicService;

    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<Topic>> list(String title, String subtitle,
                       @RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @Sort(accepts = {"id", "create_time", "price"}) @RequestParam(defaultValue = "create_time") String order,
                       @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity=PageEntity.page(pageNo,pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<Topic> topicList = topicService.listTopicPage(title, subtitle,pageEntity);
        return RestResponse.ok(topicList);
    }


    @Operation(summary = "添加")
    @PostMapping
    public RestResponse create(@RequestBody Topic topic) {
        topicService.add(topic);
        return RestResponse.success("添加成功");
    }

    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public RestResponse<TopicVO> get(@PathVariable Long id) {
        TopicVO vo=topicService.get(id);
        return RestResponse.ok(vo);
    }

    @Operation(summary = "编辑")
    @PutMapping
    public RestResponse update(@RequestBody Topic topic) {
        topicService.update(topic);
        return RestResponse.ok(topic);
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public RestResponse delete(@PathVariable Long id) {
        topicService.delete(id);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "批量删除")
    @PostMapping("/batchDelete")
    public RestResponse batchDelete(@RequestBody List<Long> ids) {
        topicService.deleteByIds(ids);
        return RestResponse.success("操作成功");
    }
}
