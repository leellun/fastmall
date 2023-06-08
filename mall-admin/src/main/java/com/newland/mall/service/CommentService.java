package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Comment;
import com.newland.mall.model.vo.wx.CommentCountVO;
import com.newland.mall.model.vo.wx.CommentVO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 评论表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface CommentService extends IService<Comment> {
    /**
     * 后台分页评论
     * @param userId 用户id
     * @param valueId
     * @param pageEntity 分页封装
     * @return
     */
    PageInfo<Comment> listComment(Long userId, Long valueId, PageEntity pageEntity);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 添加评论
     * @param comment
     */
    void addComment(Comment comment);

    /**
     * 有图和所有评论统计
     * @param type
     * @param valueId
     * @return
     */
    CommentCountVO count(Integer type, Integer valueId);

    /**
     * 评论列表
     * @param type 评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；
     * @param valueId 如果type=0，则是商品评论；如果是type=1，则是专题评论。
     * @param showType 0：全部 1：有图
     * @param pageEntity 分页
     * @return
     */
    PageInfo<CommentVO> query(Integer type, Long valueId, Integer showType, PageEntity pageEntity);
}