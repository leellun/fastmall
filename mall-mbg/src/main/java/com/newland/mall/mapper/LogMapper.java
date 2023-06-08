package com.newland.mall.mapper;

import com.newland.mall.entity.Log;
import com.newland.mybatis.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 操作日志表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface LogMapper extends BaseMapper<Log> {
    /**
     * 通过name获取日志
     * @param name
     * @return
     */
    List<Log> listByName(String name);
}