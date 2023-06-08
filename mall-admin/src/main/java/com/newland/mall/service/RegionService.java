package com.newland.mall.service;

import com.newland.mall.entity.Region;
import com.newland.mall.model.vo.RegionVO;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 行政区域表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface RegionService extends IService<Region> {
    List<Region> listByPid(Long id);

    List<RegionVO> list();
}