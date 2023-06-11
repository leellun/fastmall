package com.newland.mall.service;

import com.newland.mall.entity.Goods;
import com.newland.mall.entity.GoodsProduct;
import com.newland.mall.entity.GoodsSpec;
import com.newland.mall.model.dto.GoodsProductDTO;
import com.newland.mall.model.vo.GoodsProductVo;
import com.newland.mall.model.vo.GoodsSaleAttrVo;
import com.newland.mybatis.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 商品货品表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface GoodsProductService extends IService<GoodsProduct> {
    /**
     * 修改库存
     * @param productId 商品id
     * @param number 计数
     */
    Integer addStock(Long productId, Integer number);

    /**
     * 根据商品id获取获取产品信息
     * @param goodsId
     * @return
     */
    List<GoodsProduct> listGoodsProducts(Long goodsId);
    /**
     * 根据商品id获取获取产品信息
     * @param goodsId
     * @return
     */
    List<GoodsProductVo> listWithSpecGoodsProducts(Long goodsId);

    /**
     * 添加货品
     * @param product
     */
    void add(GoodsProduct product);

    /**
     * 货品数
     * @return
     */
    Long count();

    /**
     * 减少库存
     * @param productId
     * @param number
     * @return
     */
    int reduceStock(Long productId, Integer number);

    /**
     * 保存商品sku
     * @param goods 商品
     * @param products 产品列表
     * @param specMap 规格对应
     */
    void saveProducts(Goods goods, List<GoodsProductVo> products, List<GoodsSaleAttrVo> specMap);
    /**
     * 更新商品sku
     * @param goods 商品
     * @param products 产品列表
     * @param specMap 规格对应
     */
    void updateProducts(Goods goods, List<GoodsProductVo> products, List<GoodsSaleAttrVo> specMap);
}