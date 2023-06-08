package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Storage;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 文件存储表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface StorageService extends IService<Storage> {
    /**
     * 添加
     * @param storageInfo
     */
    void add(Storage storageInfo);

    /**
     * 根据key获取存储
     * @param key
     * @return
     */
    Storage getByKey(String key);

    /**
     * 列表
     * @param key
     * @param name
     * @param pageEntity
     * @return
     */
    PageInfo<Storage> listStoragePage(String key, String name, PageEntity pageEntity);

    /**
     * 详情
     * @param id
     * @return
     */
    Storage get(Long id);

    /**
     * 更新
     * @param storage
     */
    void update(Storage storage);

    /**
     * 通过key删除文件
     * @param key
     */
    void delete(String key);
}