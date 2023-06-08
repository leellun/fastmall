package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Log;
import com.newland.mall.mapper.LogMapper;
import com.newland.mall.service.LogService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 操作日志表 服务实现类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {
    @Override
    public PageInfo<Log> listLogPage(String name, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity,()->baseMapper.listByName(name));
    }
}