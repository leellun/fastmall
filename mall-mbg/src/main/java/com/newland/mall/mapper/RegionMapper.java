package com.newland.mall.mapper;

import com.newland.mall.entity.Region;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 行政区域表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface RegionMapper extends BaseMapper<Region> {
    /**
     * 全部获取
     * @return
     */
    List<Region> listAll();

    /**
     * 子地域
     * @param pid
     * @return
     */
    List<Region> listByPid(@Param("pid") Long pid);
}