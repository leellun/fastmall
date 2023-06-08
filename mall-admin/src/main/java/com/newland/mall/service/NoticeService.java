package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Notice;
import com.newland.mall.model.vo.NoticeCatVO;
import com.newland.mall.model.vo.NoticeVO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 通知表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface NoticeService extends IService<Notice> {
    /**
     * 列表
     * @param title
     * @param content
     * @param pageEntity
     * @return
     */
    PageInfo<Notice> listNoticePage(String title, String content, PageEntity pageEntity);

    /**
     * 添加
     * @param notice
     */

    void add(Notice notice);

    /**
     * 详情
     * @param id
     * @return
     */
    NoticeVO get(Long id);

    /**
     * 更新
     * @param notice
     */
    void update(Notice notice);

    /**
     * 删除通知
     * @param id
     */
    void delete(Long id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(List<Long> ids);

    /**
     * 获取用户查看通知
     * @param id
     * @return
     */
    NoticeCatVO getNoticeCat(Long id);
}