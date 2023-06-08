package com.newland.mall.mapper;

import com.newland.mall.entity.Config;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统配置表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface ConfigMapper extends BaseMapper<Config> {
    /**
     * 根据key获取配置 不区分是否删除
     * @param key 根据key获取配置
     * @return
     */
    Config getByKey(@Param("key") String key);

    /**
     * 类型配置列表
     * @param type
     * @return
     */
    List<Config> listByType(@Param("type") Integer type);

    /**
     * 获取所有配置
     * @return
     */
    List<Config> listAll();
}