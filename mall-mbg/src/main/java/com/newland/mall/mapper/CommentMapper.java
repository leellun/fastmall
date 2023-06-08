package com.newland.mall.mapper;

import com.newland.mall.entity.Comment;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 评论表 Mapper 接口
 *
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 根据用户id和评论相关id 获取评论
     *
     * @param userId  用户id
     * @param valueId 如果type=0，则是商品评论；如果是type=1，则是专题评论。
     * @return
     */
    List<Comment> listByUserIdAndValueId(@Param("userId") Long userId, @Param("valueId") Long valueId);

    /**
     * 统计
     *
     * @param type
     * @param valueId
     * @param hasPicture
     * @return
     */
    Long countByValueId(@Param("type") Integer type, @Param("valueId") Integer valueId, @Param("hasPicture") Integer hasPicture);

    /**
     * 获取评论列表
     * @param type
     * @param valueId
     * @param hasPicture
     * @return
     */
    List<Comment> listByTypeAndValueIdAndPicture(@Param("type") Integer type, @Param("valueId") Long valueId, @Param("hasPicture") Integer hasPicture);
}