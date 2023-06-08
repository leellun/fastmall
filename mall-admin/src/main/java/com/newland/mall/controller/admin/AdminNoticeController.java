package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Notice;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.vo.NoticeVO;
import com.newland.mall.service.NoticeAdminService;
import com.newland.mall.service.NoticeService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import com.newland.security.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Tag(name="通知管理")
@RestController
@RequestMapping("/admin/notice")
@Validated
public class AdminNoticeController {
    @Autowired
    private NoticeService noticeService;

    @Operation(summary = "查询")
    @GetMapping
    public RestResponse<PageInfo<Notice>> list(String title, String content,
                       @RequestParam(defaultValue = "1") Integer pageNo,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       @Sort @RequestParam(defaultValue = "create_time") String order,
                       @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<Notice> noticeList = noticeService.listNoticePage(title, content,pageEntity);
        return RestResponse.ok(noticeList);
    }


    private Long getAdminId(){
        return SecurityUtil.getLoginUser().getUserId();
    }

    @Operation(summary = "添加")
    @PostMapping
    public RestResponse create(@RequestBody Notice notice) {
        // 1. 添加通知记录
        notice.setAdminId(getAdminId());
        noticeService.add(notice);
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "详情")
    @GetMapping("/{id}")
    public RestResponse<NoticeVO> get(@PathVariable Long id) {
        return RestResponse.success(noticeService.get(id));
    }

    @Operation(summary = "编辑")
    @PutMapping
    public Object update(@RequestBody Notice notice) {
        // 1. 更新通知记录
        notice.setAdminId(getAdminId());
        noticeService.update(notice);
        return RestResponse.ok(notice);
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public Object delete(@RequestBody Notice notice) {
        noticeService.delete(notice.getId());
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "批量删除")
    @DeleteMapping("/batchDelete")
    public Object batchDelete(@RequestBody List<Long> ids) {
        noticeService.deleteByIds(ids);
        return RestResponse.success("操作成功");
    }
}
