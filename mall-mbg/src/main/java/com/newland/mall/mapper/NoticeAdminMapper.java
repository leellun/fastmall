package com.newland.mall.mapper;

import com.newland.mall.entity.NoticeAdmin;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 通知管理员表 Mapper 接口
 *
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface NoticeAdminMapper extends BaseMapper<NoticeAdmin> {
    /**
     * 通知id获取通知管理员表
     *
     * @param noticeId
     * @return
     */
    List<NoticeAdmin> listByNoticeId(@Param("noticeId") Long noticeId);

    /**
     * 订阅通知数
     *
     * @param noticeId
     * @return
     */
    Long countReadByNoticeId(@Param("noticeId") Long noticeId);

    /**
     * 通过通知id更新
     *
     * @param noticeAdmin
     * @param noticeId
     */
    void updateByNoticeId(@Param("noticeAdmin") NoticeAdmin noticeAdmin, @Param("noticeId") Long noticeId);

    /**
     * 删除通知关联
     *
     * @param noticeId
     */
    void deletedByNoticeId(@Param("noticeId") Long noticeId);

    /**
     * 批量删除通知关联
     *
     * @param noticeIds
     */
    void deleteByNoticeIds(@Param("noticeIds") List<Long> noticeIds);

    /**
     * 未读
     *
     * @param adminId
     * @return
     */
    int countUnread(@Param("adminId") Long adminId);

    /**
     * 通过通知id和adminid获取通知信息
     *
     * @param noticeId
     * @param adminId
     * @return
     */
    NoticeAdmin getByNoticeIdAndAdminId(@Param("noticeId") Long noticeId, @Param("adminId") Long adminId);

    /**
     * 标记已读
     *
     * @param ids
     * @param time
     */
    void updateReadTimeByIds(@Param("ids") List<Long> ids, @Param("time") LocalDateTime time);

    /**
     * 批量删除
     *
     * @param ids     id列表
     * @param adminId 当前用户
     * @param time    时间
     */
    void batchDeleteByIds(@Param("ids") List<Long> ids, @Param("adminId") Long adminId, @Param("time") LocalDateTime time);

    /**
     * 通知admin列表
     * @param title
     * @param type
     * @param adminId
     * @return
     */
    List<NoticeAdmin> listNotices(@Param("title") String title,@Param("type") String type,@Param("adminId") Long adminId);
}