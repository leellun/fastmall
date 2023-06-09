package com.newland.mall.service.impl;

import com.newland.mall.entity.Category;
import com.newland.mall.mapper.CategoryMapper;
import com.newland.mall.model.vo.CateVO;
import com.newland.mall.model.vo.CategoryVO;
import com.newland.mall.model.vo.wx.CategoryAllVO;
import com.newland.mall.model.vo.wx.CategoryDetailVO;
import com.newland.mall.service.CategoryService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类目表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public List<CategoryVO> list() {
        List<CategoryVO> categoryVoList = new ArrayList<>();
        List<Category> categoryList = baseMapper.listByPid(0L);
        for (Category category : categoryList) {
            CategoryVO categoryVO = new CategoryVO();
            BeanUtils.copyProperties(category, categoryVO);

            List<CategoryVO> children = new ArrayList<>();
            List<Category> subCategoryList = baseMapper.listByPid(category.getId());
            for (Category subCategory : subCategoryList) {
                CategoryVO subCategoryVo = new CategoryVO();
                BeanUtils.copyProperties(subCategory, subCategoryVo);
                children.add(subCategoryVo);
            }
            categoryVO.setChildren(children);
            categoryVoList.add(categoryVO);
        }
        return categoryVoList;
    }

    @Override
    public Category get(Long id) {
        Category category = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(category, "数据不存在");
        return category;
    }

    @Override
    public void add(Category category) {
        validate(category);
        baseMapper.insert(category);
    }


    @Override
    public void update(Category category) {
        validate(category);
        Category dbCategory = baseMapper.selectByPrimaryKey(category.getId());
        baseMapper.updateByPrimaryKeySelective(category);
        AssertUtil.notNull(dbCategory, "数据不存在");

    }

    @Override
    public void delete(Long id) {
        Category category = baseMapper.selectByPrimaryKey(id);
        AssertUtil.notNull(category, "数据不存在");
        Category c = new Category();
        c.setId(id);
        c.setDeleted(1);
        baseMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public List<Category> listL1() {
        return baseMapper.listByLevel("L1");
    }

    @Override
    public List<Category> listByPid(Long id) {
        return baseMapper.listByPid(id);
    }

    @Override
    public CategoryDetailVO getDetail(Long id) {
        // 所有一级分类目录
        List<Category> l1CatList = this.listL1();

        // 当前一级分类目录
        Category currentCategory = null;
        if (id != null) {
            currentCategory = baseMapper.selectByPrimaryKey(id);
        } else {
            if (l1CatList.size() > 0) {
                currentCategory = l1CatList.get(0);
            }
        }

        // 当前一级分类目录对应的二级分类目录
        List<Category> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = this.listByPid(currentCategory.getId());
        }
        CategoryDetailVO vo = new CategoryDetailVO();
        vo.setCategoryList(l1CatList);
        vo.setCurrentCategory(currentCategory);
        vo.setCurrentSubCategory(currentSubCategory);
        return vo;
    }

    /**
     * 优先从缓存中读取
     *
     * @return
     */
    @Override
    public CategoryAllVO listAll() {
        // 所有一级分类目录
        List<Category> l1CatList = this.listL1();

        //所有子分类列表
        Map<Long, List<Category>> allList = new HashMap<>();
        List<Category> sub;
        for (Category category : l1CatList) {
            sub = this.listByPid(category.getId());
            allList.put(category.getId(), sub);
        }

        // 当前一级分类目录
        Category currentCategory = l1CatList.get(0);

        // 当前一级分类目录对应的二级分类目录
        List<Category> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = this.listByPid(currentCategory.getId());
        }

        CategoryAllVO vo = new CategoryAllVO();
        vo.setCategoryList(l1CatList);
        vo.setCurrentCategory(currentCategory);
        vo.setCurrentSubCategory(currentSubCategory);
        vo.setAllList(allList);
        return vo;
    }

    @Override
    public List<Category> queryChannel() {
        return baseMapper.listChannels();
    }

    @Override
    public List<Category> listL1WithoutRecommend(Integer catlogListLimit) {
        return PageWrapper.list(1, catlogListLimit, () -> baseMapper.listL1WithoutRecommend());
    }

    @Override
    public List<CateVO> listCats() {
        List<Category> l1CatList = this.listL1();
        // 管理员设置“所属分类”
        List<CateVO> categoryList = new ArrayList<>(l1CatList.size());

        for (Category l1 : l1CatList) {
            CateVO l1CatVo = new CateVO();
            l1CatVo.setValue(l1.getId());
            l1CatVo.setLabel(l1.getName());

            List<Category> l2CatList = this.listByPid(l1.getId());
            List<CateVO> children = new ArrayList<>(l2CatList.size());
            for (Category l2 : l2CatList) {
                CateVO l2CatVo = new CateVO();
                l2CatVo.setValue(l2.getId());
                l2CatVo.setLabel(l2.getName());
                children.add(l2CatVo);
            }
            l1CatVo.setChildren(children);
            categoryList.add(l1CatVo);
        }
        return categoryList;
    }

    /**
     * 验证
     *
     * @param category
     */
    private void validate(Category category) {
        AssertUtil.isNotTrue(StringUtils.isEmpty(category.getName()), "分类名称不能为空");
        AssertUtil.isNotTrue(StringUtils.isEmpty(category.getLevel()), "等级不能为空");
        String level = category.getLevel();
        AssertUtil.isNotTrue(!level.equals("L1") && !level.equals("L2"), "等级错误");
        if (category.getPid() == null) {
            category.setPid(0L);
        }
    }
}