package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Ad;
import com.newland.mall.model.dto.AdDTO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 广告表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface AdService extends IService<Ad> {
    /**
     * 获取分页广告
     * @param name 名称
     * @param content 内容
     * @param pageEntity
     * @return
     */
    PageInfo<Ad> getPageAd(String name, String content, PageEntity pageEntity);

    /**
     * 添加
     * @param ad
     */
    void add(AdDTO ad);

    /**
     * 获取广告
     * @param id
     * @return
     */
    Ad getAd(Long id);

    /**
     * 修改
     * @param adDTO
     */
    void updateAd(AdDTO adDTO);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 首页广告
     * @return
     */
    List<Ad> queryIndex();
}