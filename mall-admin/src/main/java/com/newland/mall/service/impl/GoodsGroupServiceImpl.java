package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Category;
import com.newland.mall.entity.GoodsGroup;
import com.newland.mall.mapper.GoodsGroupMapper;
import com.newland.mall.model.vo.GoodsAttributeCategoryItemVo;
import com.newland.mall.model.vo.GoodsGroupVo;
import com.newland.mall.service.CategoryService;
import com.newland.mall.service.GoodsGroupService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 产品属性分类表 服务实现类
 *
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Service
public class GoodsGroupServiceImpl extends ServiceImpl<GoodsGroupMapper, GoodsGroup> implements GoodsGroupService {
    @Autowired
    private CategoryService categoryService;

    @Override
    public void create(GoodsGroup goodsGroup) {
        GoodsGroup dbGoodsAttributeCategory = baseMapper.getByName(goodsGroup.getName());
        AssertUtil.isNull(dbGoodsAttributeCategory, "产品属性分类已经存在");
        goodsGroup.setAttributeCount(0);
        goodsGroup.setParamCount(0);
        baseMapper.insert(goodsGroup);
    }

    @Override
    public void update(GoodsGroup goodsGroup) {
        baseMapper.updateByPrimaryKeySelective(goodsGroup);
    }

    @Override
    public void delete(Long id) {
        baseMapper.deleteLogicalByPrimaryKey(id);
    }

    @Override
    public GoodsGroupVo getItem(Long id) {
        GoodsGroupVo vo = new GoodsGroupVo();
        GoodsGroup goodsGroup = baseMapper.selectByPrimaryKey(id);
        vo.setGoodsGroup(goodsGroup);
        Long categoryId = goodsGroup.getCategoryId();
        Category category = categoryService.getById(categoryId);
        Long[] categoryIds = new Long[]{};
        if (category != null) {
            Long parentCategoryId = category.getPid();
            categoryIds = new Long[]{parentCategoryId, categoryId};
        }
        vo.setCategoryIds(Arrays.stream(categoryIds).toList());
        return vo;
    }

    @Override
    public PageInfo<GoodsGroup> getList(Integer pageSize, Integer pageNo) {
        return PageWrapper.page(PageEntity.page(pageNo, pageSize), () -> baseMapper.list());
    }

    @Override
    public List<GoodsGroup> list() {
        return baseMapper.list();
    }

    @Override
    public List<GoodsAttributeCategoryItemVo> getListWithAttr() {
        return baseMapper.listWithAttr();
    }
}