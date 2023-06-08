package com.newland.mall.mapper;

import com.newland.mall.entity.Ad;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 广告表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface AdMapper extends BaseMapper<Ad> {
    /**
     * 查询广告列表
     * @param name 广告名称
     * @param content 内容
     * @return
     */
    List<Ad> listPageAd(@Param("name") String name,@Param("content") String content);

    /**
     * 首页广告
     * @return
     */
    List<Ad> listIndex();
}