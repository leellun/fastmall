package com.newland.mall.mapper;

import com.newland.mall.entity.Storage;
import com.newland.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件存储表 Mapper 接口
 * @author leellun
 * @since 2023-02-27 22:03:47
 */
@Repository
public interface StorageMapper extends BaseMapper<Storage> {
    /**
     * 通过key获取存储
     * @param key
     * @return
     */
    Storage getByKey(@Param("key") String key);

    /**
     * 删除
     * @param key
     * @return
     */
    Integer deleteByKey(@Param("key") String key);

    /**
     * 列表查询
     * @param key
     * @param name
     * @return
     */
    List<Storage> listByKeyAndName(@Param("key") String key,@Param("name")  String name);
}