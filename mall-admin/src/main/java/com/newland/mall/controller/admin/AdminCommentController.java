package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Comment;
import com.newland.mall.model.RestResponse;
import com.newland.mall.service.CommentService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name="评论管理")
@RestController
@RequestMapping("/admin/comment")
@Validated
public class AdminCommentController {
    @Autowired
    private CommentService commentService;

    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<Comment>> list(Long userId, Long valueId,
                             @RequestParam(defaultValue = "1") Integer pageNo,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @Sort @RequestParam(defaultValue = "create_time") String order,
                             @RequestParam(defaultValue = "false") Boolean desc) {
        PageEntity pageEntity=PageEntity.page(pageNo,pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<Comment> commentList = commentService.listComment(userId, valueId, pageEntity);
        return RestResponse.ok(commentList);
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public RestResponse delete(@PathVariable Long id) {
        commentService.delete(id);
        return RestResponse.success("操作成功");
    }

}
