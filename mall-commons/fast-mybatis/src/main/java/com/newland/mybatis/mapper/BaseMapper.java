package com.newland.mybatis.mapper;

/**
 * base类型
 * @param <T>
 */
public interface BaseMapper<T> {
    /**
     * 通过主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    int deleteLogicalByPrimaryKey(Long id);

    /**
     * 新增数据
     * @param row
     * @return
     */
    int insert(T row);

    int insertSelective(T row);

    T selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(T row);

    int updateByPrimaryKey(T row);
}
