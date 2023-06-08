package com.newland.mall.controller.admin;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.NoticeAdmin;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.dto.UserPassVO;
import com.newland.mall.model.vo.NoticeCatVO;
import com.newland.mall.service.NoticeAdminService;
import com.newland.mall.service.NoticeService;
import com.newland.mall.service.SysUserService;
import com.newland.mall.validator.Sort;
import com.newland.mybatis.page.PageEntity;
import com.newland.security.utils.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author leell
 */
@Tag(name = "基本信息")
@RestController
@RequestMapping("/admin/profile")
@Validated
public class AdminProfileController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private NoticeAdminService noticeAdminService;
    @Autowired
    private NoticeService noticeService;

    @Operation(summary = "重置密码")
    @PostMapping("/password")
    public RestResponse create(@RequestBody UserPassVO userPassVO) {
        sysUserService.updatePass(userPassVO);
        return RestResponse.success("操作成功");
    }

    private Long getAdminId() {
        return SecurityUtil.getLoginUser().getUserId();
    }

    @Operation(summary = "未读数目")
    @GetMapping("/nnotice")
    public Object nNotice() {
        int count = noticeAdminService.countUnread(getAdminId());
        return RestResponse.ok(count);
    }

    @Operation(summary = "用户通知列表")
    @GetMapping("/lsnotice")
    public RestResponse<PageInfo<NoticeAdmin>> lsNotice(String title, String type,
                                                        @RequestParam(defaultValue = "1") Integer pageNo,
                                                        @RequestParam(defaultValue = "10") Integer pageSize,
                                                        @Sort @RequestParam(defaultValue = "create_time") String order,
                                                        @RequestParam(required = false) Boolean desc) {
        PageEntity pageEntity = PageEntity.page(pageNo, pageSize);
        pageEntity.setOrder(order);
        pageEntity.setDesc(desc);
        PageInfo<NoticeAdmin> noticeList = noticeAdminService.listNoticeAdmins(title, type, getAdminId(), pageEntity);
        return RestResponse.ok(noticeList);
    }

    @Operation(summary = "用户通知查看")
    @PostMapping("/catnotice/{noticeId}")
    public RestResponse<NoticeCatVO> catNotice(@PathVariable Long noticeId) {
        noticeAdminService.catNotice(noticeId, getAdminId());
        NoticeCatVO vo = noticeService.getNoticeCat(noticeId);
        return RestResponse.ok(vo);
    }

    @Operation(summary = "通知标记已读")
    @PostMapping("/bcatnotice")
    public RestResponse bcatNotice(@RequestBody List<Long> ids) {
        noticeAdminService.markReadByIds(ids, getAdminId());
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "删除通知")
    @PostMapping("/rmnotice/{id}")
    public Object rmNotice(@PathVariable Long id) {
        noticeAdminService.delete(id, getAdminId());
        return RestResponse.success("操作成功");
    }

    @Operation(summary = "批量删除通知")
    @DeleteMapping("/brmnotice")
    public Object brmNotice(@RequestBody List<Long> ids) {
        noticeAdminService.deleteByIds(ids, getAdminId());
        return RestResponse.success("操作成功");
    }

}
