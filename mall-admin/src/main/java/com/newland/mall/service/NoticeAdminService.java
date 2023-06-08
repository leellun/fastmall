package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.NoticeAdmin;
import com.newland.mall.model.vo.NoticeCatVO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 通知管理员表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface NoticeAdminService extends IService<NoticeAdmin> {
    /**
     * 根据通知id获取通知指定用户信息
     * @param noticeId
     * @return
     */
    List<NoticeAdmin> listByNoticeId(Long noticeId);

    /**
     * 查看订阅通知数
     * @param noticeId
     * @return
     */
    Long countReadByNoticeId(Long noticeId);

    /**
     * 更新指定通知id对应通知
     * @param noticeAdmin
     * @param noticeId
     */
    void updateByNoticeId(NoticeAdmin noticeAdmin, Long noticeId);

    /**
     * 删除通知关联通知admin
     * @param noticeId
     */
    void deleteByNoticeId(Long noticeId);

    /**
     * 批量删除
     * @param noticeIds
     */
    void deleteByNoticeIds(List<Long> noticeIds);

    /**
     * 未读
     * @param adminId
     * @return
     */
    int countUnread(Long adminId);

    /**
     * 当前用户通知列表
     * @param title
     * @param type
     * @param adminId
     * @param pageEntity
     * @return
     */
    PageInfo<NoticeAdmin> listNoticeAdmins(String title, String type, Long adminId, PageEntity pageEntity);

    /**
     * 通知查看
     * @param noticeId
     * @param adminId
     * @return
     */
    void catNotice(Long noticeId,Long adminId);

    /**
     * 标记已读
     * @param ids 通知id列表
     * @param adminId 当前用户
     */
    void markReadByIds(List<Long> ids, Long adminId);

    /**
     * 删除
     * @param id
     * @param adminId
     */
    void delete(Long id, Long adminId);

    /**
     * 批量删除
     * @param ids
     * @param adminId
     */
    void deleteByIds(List<Long> ids, Long adminId);
}