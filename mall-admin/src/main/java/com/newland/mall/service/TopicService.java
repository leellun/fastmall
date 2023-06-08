package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Topic;
import com.newland.mall.model.vo.TopicVO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 专题表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface TopicService extends IService<Topic> {
    /**
     * 分页列表
     * @param title
     * @param subtitle
     * @param pageEntity
     * @return
     */
    PageInfo<Topic> listTopicPage(String title, String subtitle, PageEntity pageEntity);

    /**
     * 添加
     * @param topic
     */
    void add(Topic topic);

    /**
     * 详情
     * @param id
     * @return
     */

    TopicVO get(Long id);

    /**
     * 更新主题
     * @param topic
     */

    void update(Topic topic);

    /**
     * 删除
     * @param id
     */

    void delete(Long id);

    /**
     * 批量删除
     * @param ids
     */

    void deleteByIds(List<Long> ids);

    /**
     * 获取指定数量主题
     * @param pageSize
     * @return
     */
    List<Topic> queryList(Integer pageSize);

    /**
     * 相关主题
     * @param id
     * @param offset
     * @param limit
     * @return
     */
    List<Topic> listRelatedToptics(Long id, int offset, int limit);
}