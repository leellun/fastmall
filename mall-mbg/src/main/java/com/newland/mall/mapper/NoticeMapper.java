package com.newland.mall.mapper;

import com.newland.mall.entity.Notice;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 通知表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface NoticeMapper extends BaseMapper<Notice> {
    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 通过标题和内容获取
     * @param title
     * @param content
     * @return
     */
    List<Notice> listByTitleAndContent(@Param("title") String title,@Param("content") String content);
}