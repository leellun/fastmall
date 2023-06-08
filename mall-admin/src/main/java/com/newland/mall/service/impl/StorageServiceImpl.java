package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Issue;
import com.newland.mall.entity.Storage;
import com.newland.mall.mapper.StorageMapper;
import com.newland.mall.service.StorageService;
import com.newland.mall.storage.IStorage;
import com.newland.mall.storage.MallStorageService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mall.utils.CharUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * 文件存储表 服务实现类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {
    @Override
    public void add(Storage storageInfo) {
        baseMapper.insert(storageInfo);
    }

    @Override
    public Storage getByKey(String key) {
        return baseMapper.getByKey(key);
    }

    @Override
    public PageInfo<Storage> listStoragePage(String key, String name, PageEntity pageEntity) {
        return PageWrapper.page(pageEntity,()->baseMapper.listByKeyAndName(key,name));
    }


    @Override
    public Storage get(Long id) {
        Storage dbStorage = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(dbStorage, "数据不存在");
        return dbStorage;
    }

    @Override
    public void update(Storage storage) {
        Storage dbStorage = baseMapper.selectByPrimaryKey(storage.getId());
        AssertUtil.notNull(dbStorage, "数据不存在");
        baseMapper.updateByPrimaryKeySelective(storage);
    }

    @Override
    public void delete(String key) {
        AssertUtil.isTrue(baseMapper.deleteByKey(key) > 0, "删除失败");
    }
}