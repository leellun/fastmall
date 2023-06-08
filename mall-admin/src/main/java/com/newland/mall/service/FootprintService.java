package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Footprint;
import com.newland.mall.model.vo.wx.FootprintVO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

/**
 * 用户浏览足迹表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface FootprintService extends IService<Footprint> {
    /**
     * 足迹列表
     * @param userId
     * @param goodsId
     * @param pageEntity
     * @return
     */
    PageInfo<Footprint> listFootprints(Long userId, Long goodsId, PageEntity pageEntity);

    /**
     * 删除足迹
     * @param userId
     * @param footprintId
     */
    void delete(Long userId, Long footprintId);

    /**
     * 用户足迹
     * @param userId
     * @param pageEntity
     * @return
     */
    PageInfo<FootprintVO> listMyFootprints(Long userId, PageEntity pageEntity);

    /**
     * 添加用户足迹
     * @param footprint
     */
    void addFootprint(Footprint footprint);
}