package com.newland.mall.mapper;

import com.newland.mall.entity.Topic;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 专题表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface TopicMapper extends BaseMapper<Topic> {
    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     *列表
     * @param title
     * @param subtitle
     * @return
     */
    List<Topic> listByTitleAndSubtitle(@Param("title") String title,@Param("subtitle") String subtitle);

    /**
     * 非id列表
     * @param id
     * @return
     */
    List<Topic> listByNotId(@Param("id") Long id);
}