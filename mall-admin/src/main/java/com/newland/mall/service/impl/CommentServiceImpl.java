package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Comment;
import com.newland.mall.entity.User;
import com.newland.mall.enumeration.BasicEnum;
import com.newland.mall.mapper.CommentMapper;
import com.newland.mall.mapper.GoodsMapper;
import com.newland.mall.model.vo.wx.CommentCountVO;
import com.newland.mall.model.vo.wx.CommentVO;
import com.newland.mall.model.vo.wx.UserInfoVO;
import com.newland.mall.service.CommentService;
import com.newland.mall.service.GoodsService;
import com.newland.mall.service.TopicService;
import com.newland.mall.service.UserService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评论表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private TopicService topicService;

    @Override
    public PageInfo<Comment> listComment(Long userId, Long valueId, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity, () -> baseMapper.listByUserIdAndValueId(userId, valueId));
    }

    @Override
    public void delete(Long id) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setDeleted(1);
        AssertUtil.isTrue(baseMapper.updateByPrimaryKeySelective(comment) > 0, "删除失败");
    }

    @Override
    public void addComment(Comment comment) {
        validate(comment);
        baseMapper.insertSelective(comment);
    }

    @Override
    public CommentCountVO count(Integer type, Integer valueId) {
        Long allCount = baseMapper.countByValueId(type, valueId, null);
        Long hasPicCount = baseMapper.countByValueId(type, valueId, BasicEnum.YES.getKey());
        CommentCountVO vo = new CommentCountVO();
        vo.setAllCount(allCount);
        vo.setHasPicCount(hasPicCount);
        return vo;
    }

    @Override
    public PageInfo<CommentVO> query(Integer type, Long valueId, Integer showType, PageEntity pageEntity) {
        PageInfo<Comment> pageInfo = PageWrapper.page(pageEntity, () -> baseMapper.listByTypeAndValueIdAndPicture(type, valueId, showType == 0 ? null : BasicEnum.YES.getKey()));
        List<Comment> commentList = pageInfo.getList();
        List<CommentVO> commentVoList = new ArrayList<>(commentList.size());
        for (Comment comment : commentList) {
            CommentVO commentVO = new CommentVO();
            commentVO.setContent(comment.getContent());
            commentVO.setCreateTime(comment.getCreateTime());
            commentVO.setAdminContent(comment.getAdminContent());
            commentVO.setPicUrls(comment.getPicUrls());
            commentVO.setStar(comment.getStar());
            User user = userService.getInfo(comment.getUserId());
            UserInfoVO userInfo = new UserInfoVO();
            userInfo.setNickName(user.getNickname());
            userInfo.setAvatarUrl(user.getAvatar());
            commentVO.setUserInfo(userInfo);
            commentVoList.add(commentVO);
        }

        return PageWrapper.newPageInfo(pageInfo, commentVoList);
    }

    /**
     * 参数验证
     *
     * @param comment
     */
    private void validate(Comment comment) {
        Integer type = comment.getType();
        Long valueId = comment.getValueId();
        if (type == 0) {
            AssertUtil.notNull(goodsMapper.selectByPrimaryKey(valueId), "商品不存在");
        } else if (type == 1) {
            AssertUtil.notNull(topicService.getById(valueId), "主题不存在");
        }
        Integer hasPicture = comment.getHasPicture();
        if (hasPicture == null || hasPicture == 0) {
            comment.setPicUrls(new String[0]);
        }
    }
}