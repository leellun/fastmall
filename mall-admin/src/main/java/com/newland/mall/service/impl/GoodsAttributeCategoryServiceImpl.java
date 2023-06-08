package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.GoodsAttributeCategory;
import com.newland.mall.mapper.GoodsAttributeCategoryMapper;
import com.newland.mall.model.vo.GoodsAttributeCategoryItemVo;
import com.newland.mall.service.GoodsAttributeCategoryService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 产品属性分类表 服务实现类
 *
 * @author leellun
 * @since 2023-06-04 21:50:54
 */
@Service
public class GoodsAttributeCategoryServiceImpl extends ServiceImpl<GoodsAttributeCategoryMapper, GoodsAttributeCategory> implements GoodsAttributeCategoryService {

    @Override
    public void create(String name) {
        GoodsAttributeCategory dbGoodsAttributeCategory = baseMapper.getByName(name);
        AssertUtil.isNull(dbGoodsAttributeCategory, "产品属性分类已经存在");
        GoodsAttributeCategory goodsAttributeCategory = new GoodsAttributeCategory();
        goodsAttributeCategory.setName(name);
        goodsAttributeCategory.setAttributeCount(0);
        goodsAttributeCategory.setParamCount(0);
        baseMapper.insert(goodsAttributeCategory);
    }

    @Override
    public void update(Long id, String name) {
        GoodsAttributeCategory goodsAttributeCategory = new GoodsAttributeCategory();
        goodsAttributeCategory.setName(name);
        goodsAttributeCategory.setId(id);
        baseMapper.updateByPrimaryKeySelective(goodsAttributeCategory);
    }

    @Override
    public void delete(Long id) {
        baseMapper.deleteLogicalByPrimaryKey(id);
    }

    @Override
    public GoodsAttributeCategory getItem(Long id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<GoodsAttributeCategory> getList(Integer pageSize, Integer pageNo) {
        return PageWrapper.page(PageEntity.page(pageNo, pageSize), () -> baseMapper.list());
    }

    @Override
    public List<GoodsAttributeCategory> list() {
        return baseMapper.list();
    }

    @Override
    public List<GoodsAttributeCategoryItemVo> getListWithAttr() {
        return baseMapper.listWithAttr();
    }
}