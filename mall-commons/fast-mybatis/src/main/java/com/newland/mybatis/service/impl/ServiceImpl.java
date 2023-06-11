package com.newland.mybatis.service.impl;

import com.newland.mybatis.mapper.BaseMapper;
import com.newland.mybatis.service.IService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * 服务基类实现
 * Date: 2023/2/21 13:09:22
 *
 * @author leell
 */
@SuppressWarnings("unchecked")
public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {
    static int DEFAULT_BATCH_SIZE = 1000;
    @Autowired
    protected M baseMapper;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public T getById(Long id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    public int updateById(T t) {
        return baseMapper.updateByPrimaryKeySelective(t);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatch(Collection<T> entityList) {
        return saveBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatch(Collection<T> entityList, int batchSize) {
        if (entityList.size() == 0) {
            return false;
        }
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        M mapper = (M) session.getMapper(getMapperClass());
        int i = 0;
        for (T entity : entityList) {
            i++;
            mapper.insertSelective(entity);
            if (i % batchSize == 0) {
                session.commit();
                session.clearCache();
            }
        }
        session.commit();
        session.clearCache();
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatch(Collection<T> entityList) {
        return this.updateBatch(entityList, DEFAULT_BATCH_SIZE);
    }

    public boolean updateBatch(Collection<T> entityList, int batchSize) {
        if (entityList.size() == 0) {
            return false;
        }
        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        M mapper = (M) session.getMapper(getMapperClass());
        int i = 0;
        int count = 0;
        for (T entity : entityList) {
            i++;
            count += mapper.updateByPrimaryKeySelective(entity);
            if (i % batchSize == 0) {
                session.commit();
                session.clearCache();
            }
        }
        session.commit();
        session.clearCache();
        return count == entityList.size();
    }

    private Class<M> getMapperClass() {
        Class<?>[] clazzes = baseMapper.getClass().getInterfaces();
        for (Class<?> clazz : clazzes) {
            if (BaseMapper.class.isAssignableFrom(clazz)) {
                return (Class<M>) clazz;
            }
        }
        return null;
    }
}
