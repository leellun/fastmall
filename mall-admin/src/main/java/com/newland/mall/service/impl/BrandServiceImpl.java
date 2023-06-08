package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Brand;
import com.newland.mall.mapper.BrandMapper;
import com.newland.mall.service.BrandService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 品牌商表 服务实现类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
    @Override
    public PageInfo<Brand> listPage(String id, String name, PageEntity pageEntity) {
        PageInfo<Brand> pageInfo= PageWrapper.page(pageEntity,()->baseMapper.listBrand(id,name));
        return pageInfo;
    }

    @Override
    public List<Brand> all() {
        return baseMapper.listAll();
    }

    @Override
    public List<Brand> query(Integer pageSize) {
        return null;
    }

    @Override
    public void add(Brand brand) {
        validate(brand);
        baseMapper.insert(brand);
    }

    @Override
    public Brand get(Long id) {
        Brand brand=baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(brand,"品牌不存在");
        return brand;
    }

    @Override
    public void update(Brand brand) {
        validate(brand);
        AssertUtil.notNull(brand.getId(),"品牌不存在");
        Brand dbBrand=baseMapper.selectByPrimaryKey(brand.getId());
        AssertUtil.notNull(dbBrand,"品牌不存在");
        baseMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public void delete(Long id) {
        Brand brand=baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(brand,"品牌不存在");
        baseMapper.deleteLogicalByPrimaryKey(id);
    }

    private void validate(Brand brand) {
        AssertUtil.isNotTrue(StringUtils.isEmpty( brand.getName()),"品牌名称不能为空");
        AssertUtil.isNotTrue(StringUtils.isEmpty( brand.getDescription()),"品牌描述不能为空");
        AssertUtil.notNull(brand.getFloorPrice(),"品牌价格未设置");
    }
}