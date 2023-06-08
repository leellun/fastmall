package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Log;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

/**
 * 操作日志表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface LogService extends IService<Log> {
    /**
     * 分页获取日志
     * @param name
     * @param pageEntity
     * @return
     */
    PageInfo<Log> listLogPage(String name, PageEntity pageEntity);
}