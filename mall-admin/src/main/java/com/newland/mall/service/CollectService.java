package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Collect;
import com.newland.mall.model.dto.wx.ValueCollectVO;
import com.newland.mall.model.vo.wx.CollectVO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

/**
 * 收藏表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface CollectService extends IService<Collect> {
    /**
     * 获取收藏列表
     * @param userId
     * @param valueId
     * @param pageEntity
     * @return
     */
    PageInfo<Collect> listCollect(Long userId, Long valueId, PageEntity pageEntity);
    /**
     * 获取收藏列表
     * @param userId
     * @param type 类型，如果是0则是商品收藏，如果是1则是专题收藏
     * @param pageEntity
     * @return
     */
    PageInfo<CollectVO> listCollect(Long userId, Integer type, PageEntity pageEntity);

    /**
     * 收藏或取消收藏
     * @param userId 用户id
     * @param valueCollectVO 收藏
     */
    Boolean addOrDelete(Long userId, ValueCollectVO valueCollectVO);

    /**
     * 统计用户收藏
     * @param userId 用户id
     * @param type 收藏类型
     * @param valueId 对应id
     * @return
     */
    int count(Long userId, Integer type, Long valueId);
}