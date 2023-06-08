package com.newland.mall.mapper;

import com.newland.mall.entity.Category;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类目表 Mapper 接口
 *
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 获取子分类
     *
     * @param pid 父id
     * @return
     */
    List<Category> listByPid(@Param("pid") Long pid);

    /**
     * 获取指定级别的分类
     *
     * @param level
     * @return
     */
    List<Category> listByLevel(@Param("level") String level);

    /**
     * 首页分类
     *
     * @return
     */
    List<Category> listChannels();

    /**
     * 非推荐的L1
     * @return
     */
    List<Category> listL1WithoutRecommend();
}