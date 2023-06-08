package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Issue;
import com.newland.mall.entity.Notice;
import com.newland.mall.entity.NoticeAdmin;
import com.newland.mall.entity.SysUser;
import com.newland.mall.mapper.NoticeMapper;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.vo.NoticeCatVO;
import com.newland.mall.model.vo.NoticeVO;
import com.newland.mall.service.NoticeAdminService;
import com.newland.mall.service.NoticeService;
import com.newland.mall.service.SysUserService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通知表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private NoticeAdminService noticeAdminService;

    @Override
    public PageInfo<Notice> listNoticePage(String title, String content, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listByTitleAndContent(title, content));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Notice notice) {
        validate(notice);
        baseMapper.insert(notice);
        // 2. 添加管理员通知记录
        List<SysUser> adminList = sysUserService.all();
        List<NoticeAdmin> noticeAdmins = new ArrayList<>();
        for (SysUser admin : adminList) {
            NoticeAdmin noticeAdmin = new NoticeAdmin();
            noticeAdmin.setNoticeId(notice.getId());
            noticeAdmin.setNoticeTitle(notice.getTitle());
            noticeAdmin.setAdminId(admin.getId());
            noticeAdmins.add(noticeAdmin);
        }
        noticeAdminService.saveBatch(noticeAdmins);
    }

    @Override
    public void update(Notice notice) {
        validate(notice);
        Notice dbNotice = baseMapper.selectByPrimaryKey(notice.getId());
        AssertUtil.notNull(dbNotice, "数据不存在");
        // 如果通知已经有人阅读过，则不支持编辑
        AssertUtil.isNotTrue(noticeAdminService.countReadByNoticeId(notice.getId()) > 0, "通知已被阅读，不能重新编辑");
        // 1. 更新通知记录
        baseMapper.updateByPrimaryKeySelective(notice);
        // 2. 更新管理员通知记录
        if (!dbNotice.getTitle().equals(notice.getTitle())) {
            NoticeAdmin noticeAdmin = new NoticeAdmin();
            noticeAdmin.setNoticeTitle(notice.getTitle());
            noticeAdminService.updateByNoticeId(noticeAdmin, notice.getId());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Notice t = new Notice();
        t.setId(id);
        t.setDeleted(1);
        AssertUtil.isTrue(baseMapper.updateByPrimaryKeySelective(t) > 0, "删除失败");
        noticeAdminService.deleteByNoticeId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(List<Long> ids) {
        // 1. 删除通知管理员记录
        noticeAdminService.deleteByNoticeIds(ids);
        // 2. 删除通知记录
        baseMapper.deleteByIds(ids);
    }

    @Override
    public NoticeCatVO getNoticeCat(Long id) {
        NoticeCatVO vo = new NoticeCatVO();
        Notice notice = baseMapper.selectByPrimaryKey(id);
        // 返回通知的相关信息
        vo.setTitle(notice.getTitle());
        vo.setContent(notice.getContent());
        vo.setTime(notice.getUpdateTime());
        if (notice.getAdminId().equals(0)) {
            vo.setAdmin("系统");
        } else {
            SysUser admin = sysUserService.getById(notice.getAdminId());
            vo.setAdmin(admin.getUsername());
            vo.setAvatar(admin.getAvatar());
        }
        return vo;
    }

    @Override
    public NoticeVO get(Long id) {
        Notice notice = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(notice, "数据不存在");
        List<NoticeAdmin> noticeAdminList = noticeAdminService.listByNoticeId(id);
        NoticeVO vo = new NoticeVO();
        vo.setNotice(notice);
        vo.setNoticeAdminList(noticeAdminList);
        return vo;
    }

    private void validate(Notice notice) {
        AssertUtil.isNotTrue(StringUtils.isEmpty(notice.getTitle()), "标题不能为空");
    }
}