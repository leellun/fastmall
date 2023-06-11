package com.newland.mybatis.service;

import com.newland.mall.model.BaseEntity;

import java.util.Collection;


public interface IService<T> {
    /**
     * 通过id获取entity
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 更新
     * @param t
     * @return
     */
    int updateById(T t);
    /**
     * 批量插入
     * @param entityList 插入列表
     * @return 结果
     */
    public boolean saveBatch(Collection<T> entityList);

    /**
     * 批量插入
     * @param entityList 插入列表
     * @param batchSize 提交
     * @return 结果
     */
    boolean saveBatch(Collection<T> entityList, int batchSize);
    /**
     * 批量插入
     * @param entityList 插入列表
     * @return  结果
     */
    public boolean updateBatch(Collection<T> entityList);

    /**
     * 批量插入
     * @param entityList 插入列表
     * @param batchSize 提交
     * @return 结果
     */
    boolean updateBatch(Collection<T> entityList, int batchSize);
}
