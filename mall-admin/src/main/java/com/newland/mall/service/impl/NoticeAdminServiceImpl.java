package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Notice;
import com.newland.mall.entity.NoticeAdmin;
import com.newland.mall.entity.SysUser;
import com.newland.mall.mapper.NoticeAdminMapper;
import com.newland.mall.model.vo.NoticeCatVO;
import com.newland.mall.service.NoticeAdminService;
import com.newland.mall.service.NoticeService;
import com.newland.mall.service.SysUserService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通知管理员表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class NoticeAdminServiceImpl extends ServiceImpl<NoticeAdminMapper, NoticeAdmin> implements NoticeAdminService {
    @Override
    public List<NoticeAdmin> listByNoticeId(Long noticeId) {
        return baseMapper.listByNoticeId(noticeId);
    }

    @Override
    public Long countReadByNoticeId(Long noticeId) {
        return baseMapper.countReadByNoticeId(noticeId);
    }

    @Override
    public void updateByNoticeId(NoticeAdmin noticeAdmin, Long noticeId) {
        baseMapper.updateByNoticeId(noticeAdmin, noticeId);
    }

    @Override
    public void deleteByNoticeId(Long noticeId) {
        baseMapper.deletedByNoticeId(noticeId);
    }

    @Override
    public void deleteByNoticeIds(List<Long> noticeIds) {
        baseMapper.deleteByNoticeIds(noticeIds);
    }

    @Override
    public int countUnread(Long adminId) {
        return baseMapper.countUnread(adminId);
    }

    @Override
    public PageInfo<NoticeAdmin> listNoticeAdmins(String title, String type, Long adminId, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listNotices(title, type, adminId));
    }

    @Override
    public void catNotice(Long noticeId, Long adminId) {
        NoticeAdmin noticeAdmin = baseMapper.getByNoticeIdAndAdminId(noticeId, adminId);
        AssertUtil.notNull(noticeAdmin, "通知不存在");
        // 更新通知记录中的时间
        noticeAdmin.setReadTime(LocalDateTime.now());
        baseMapper.updateByPrimaryKeySelective(noticeAdmin);
    }

    @Override
    public void markReadByIds(List<Long> ids, Long adminId) {
        baseMapper.updateReadTimeByIds(ids, LocalDateTime.now());
    }

    @Override
    public void delete(Long id, Long adminId) {
        NoticeAdmin noticeAdmin = new NoticeAdmin();
        noticeAdmin.setUpdateTime(LocalDateTime.now());
        noticeAdmin.setDeleted(1);
        noticeAdmin.setId(id);
        int count = baseMapper.updateByPrimaryKeySelective(noticeAdmin);
        AssertUtil.isTrue(count > 0, "删除失败");
    }

    @Override
    public void deleteByIds(List<Long> ids, Long adminId) {
        baseMapper.batchDeleteByIds(ids, adminId, LocalDateTime.now());
    }
}