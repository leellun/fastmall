package com.newland.mall.service;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.Goods;
import com.newland.mall.model.RestResponse;
import com.newland.mall.model.dto.GoodsAllinoneDTO;
import com.newland.mall.model.vo.BrandAndCategoryVO;
import com.newland.mall.model.vo.GoodsAllinoneVO;
import com.newland.mall.model.vo.wx.GoodsDetailVO;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品基本信息表 服务类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface GoodsService extends IService<Goods> {
    /**
     * 商品列表
     *
     * @param goodsId    商品id
     * @param name       名称
     * @param pageEntity 分页entity
     * @return
     */
    PageInfo<Goods> list(Integer goodsId, String name, PageEntity pageEntity);

    /**
     * 商品详情
     *
     * @param id 商品id
     * @return
     */
    GoodsAllinoneVO getDetail(Long id);

    /**
     * 购物车品牌和分类
     *
     * @return
     */
    BrandAndCategoryVO listCatAndBrands();

    /**
     * 添加商品
     *
     * @param goodsAllinone
     */
    void create(GoodsAllinoneDTO goodsAllinone);

    /**
     * 更新商品
     *
     * @param id
     * @param goodsAllinone
     */

    void update(Long id, GoodsAllinoneDTO goodsAllinone);

    /**
     * 删除商品
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 通过商品id查询商品
     *
     * @param goodsIds
     * @return
     */
    List<Goods> listByGoodIds(Long[] goodsIds);

    /**
     * 统计所有商品
     *
     * @return
     */
    Long count();

    /**
     * 获取商品详情页详情
     *
     * @param id 商品id
     * @return
     */
    GoodsDetailVO getGoodsDetail(Long id);

    /**
     * 商品列表
     *
     * @param categoryId 分类
     * @param brandId    品牌
     * @param keyword    关键字
     * @param isHot      热
     * @param isNew      新
     * @param pageEntity 分页
     * @return
     */
    PageInfo<Goods> listSelective(Integer categoryId, Integer brandId, String keyword, Integer isHot, Integer isNew, PageEntity pageEntity);

    /**
     * 相关产品
     *
     * @param id
     * @return
     */
    List<Goods> listRelated(Long id);

    /**
     * 新品
     *
     * @param newLimit
     * @return
     */
    List<Goods> listByNew(Integer newLimit);

    /**
     * 热品
     * @param hotLimit
     * @return
     */
    List<Goods> listByHot(Integer hotLimit);

    /**
     * 指定分类下的商品
     * @param l2List
     * @param catlogMoreLimit
     * @return
     */
    List<Goods> listByCategory(List<Long> l2List, Integer catlogMoreLimit);

    /**
     * 获取在售商品
     * @param id
     * @return
     */
    Goods getOnSaleGoods(Long id);

    /**
     * 是否收藏
     * @param userId
     * @param id
     * @return
     */
    Boolean checkCollect(Long userId, Long id);
}