package com.newland.mall.service;

import com.newland.mall.entity.Category;
import com.newland.mall.model.vo.CateVO;
import com.newland.mall.model.vo.CategoryVO;
import com.newland.mall.model.vo.wx.CategoryAllVO;
import com.newland.mall.model.vo.wx.CategoryDetailVO;
import com.newland.mybatis.service.IService;

import java.util.List;

/**
 * 类目表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface CategoryService extends IService<Category> {
    /**
     * 分类列表
     * @return
     */
    List<CategoryVO> list();

    /**
     * 添加
     * @param category
     */
    void add(Category category);

    /**
     * 获取
     * @param id
     * @return
     */
    Category get(Long id);

    /**
     * 更新
     * @param category
     */
    void update(Category category);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 一级分类
     * @return
     */
    List<Category> listL1();

    /**
     * 根据父id获取分类
     * @param id
     * @return
     */
    List<Category> listByPid(Long id);

    /**
     * 分类详情
     * @param id
     * @return
     */
    CategoryDetailVO getDetail(Long id);

    /**
     * 所有分类
     * @return
     */
    CategoryAllVO listAll();

    /**
     * 首页分类
     * @return
     */
    List<Category> queryChannel();

    /**
     * 获取非推荐分类
     * @param catlogListLimit
     * @return
     */
    List<Category> listL1WithoutRecommend(Integer catlogListLimit);

    /**
     * 分类选择
     * @return
     */
    List<CateVO> listCats();
}