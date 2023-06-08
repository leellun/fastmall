package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Feedback;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.FeedbackService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author leellun
 * @date 2018/8/26 1:11
 */
@Tag(name="意见反馈")
@RestController
@RequestMapping("/admin/feedback")
@Validated
public class AdminFeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<Feedback>> list(Integer userId, String username,
                       @RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @Sort @RequestParam(defaultValue = "create_time") String order,
                       @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity=PageEntity.page(pageNo,pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<Feedback> feedbackList = feedbackService.listFeeks(userId, username, pageEntity);
        return RestResponse.ok(feedbackList);
    }
}
