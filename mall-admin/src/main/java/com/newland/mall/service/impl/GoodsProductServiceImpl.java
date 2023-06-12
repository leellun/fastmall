package com.newland.mall.service.impl;

import com.newland.mall.entity.Goods;
import com.newland.mall.entity.GoodsProduct;
import com.newland.mall.entity.GoodsProductSpecRelation;
import com.newland.mall.entity.GoodsSpec;
import com.newland.mall.mapper.GoodsProductMapper;
import com.newland.mall.mapper.GoodsProductSpecRelationMapper;
import com.newland.mall.model.dto.GoodsProductDTO;
import com.newland.mall.model.vo.GoodsProductVo;
import com.newland.mall.model.vo.GoodsSaleAttrVo;
import com.newland.mall.service.CartService;
import com.newland.mall.service.GoodsProductService;
import com.newland.mall.service.GoodsProductSpecRelationService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mall.utils.GoodsUtil;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品货品表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class GoodsProductServiceImpl extends ServiceImpl<GoodsProductMapper, GoodsProduct> implements GoodsProductService {
    @Autowired
    private GoodsProductSpecRelationService goodsProductSpecRelationService;
    @Autowired
    private GoodsProductSpecRelationMapper goodsProductSpecRelationMapper;
    private CartService cartService;

    public GoodsProductServiceImpl(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    public Integer addStock(Long productId, Integer number) {
        return baseMapper.updateStock(productId, number);
    }

    @Override
    public List<GoodsProduct> listGoodsProducts(Long goodsId) {
        return baseMapper.listByGoodsId(goodsId);
    }

    @Override
    public List<GoodsProductVo> listWithSpecGoodsProducts(Long goodsId) {
        return baseMapper.listWithSpecByGoodsId(goodsId);
    }

    @Override
    public void add(GoodsProduct product) {
        baseMapper.insert(product);
    }

    @Override
    public Long count() {
        return baseMapper.count();
    }

    @Override
    public int reduceStock(Long productId, Integer number) {
        return baseMapper.reduceStock(productId, number);
    }

    @Override
    public void saveProducts(Goods goods, List<GoodsProductVo> products, List<GoodsSaleAttrVo> specMap) {
        Long goodsId = goods.getId();

        // 商品货品表_product
        for (GoodsProductVo productVo : products) {
            productVo.setGoodsId(goodsId);
            this.saveProduct(productVo, productVo.getGoodsProductSpecRelations(), specMap);
        }
    }

    @Override
    public void updateProducts(Goods goods, List<GoodsProductVo> products, List<GoodsSaleAttrVo> specMap) {
        Long goodsId = goods.getId();
        List<Long> productIdsDb = baseMapper.listByGoodsId(goodsId).stream().map(GoodsProduct::getId).collect(Collectors.toList());
        List<Long> produdtIdList = new ArrayList<>();

        // 商品货品表_product
        for (GoodsProductVo productVo : products) {
            GoodsProductVo product = productVo;
            if (product.getId() != null && productIdsDb.contains(product.getId())) {
                produdtIdList.add(product.getId());
                baseMapper.updateByPrimaryKeySelective(product);
                cartService.updateProduct(product.getId(), product.getProductSn(), goods.getName(), product.getPrice(), product.getUrl());
            } else {
                product.setGoodsId(goodsId);
                this.saveProduct(product, productVo.getGoodsProductSpecRelations(), specMap);
            }
        }
        productIdsDb.removeAll(produdtIdList);
        if (productIdsDb.size() > 0) {
            baseMapper.deleteByIds(productIdsDb);
            goodsProductSpecRelationMapper.deleteByProductIds(productIdsDb);
        }
        cartService.deleteProductsCart(productIdsDb);
    }

    /**
     * 保存商品
     *
     * @param product 商品
     */
    private void saveProduct(GoodsProduct product, List<GoodsProductSpecRelation> goodsSpecRelations, List<GoodsSaleAttrVo> goodsSaleAttrVos) {
        baseMapper.insertSelective(product);
        Map<String, GoodsSpec> specMap = GoodsUtil.getGoodsSpecMap(goodsSaleAttrVos);
        for (int i = 0; i < goodsSpecRelations.size(); i++) {
            GoodsProductSpecRelation relation = goodsSpecRelations.get(i);
            GoodsSpec goodsSpec = specMap.get(GoodsUtil.key(relation.getName(), relation.getValue()));
            AssertUtil.notNull(goodsSpec, "规格不存在");
            relation.setItemSort(i);
            relation.setSpecId(goodsSpec.getId());
            relation.setProductId(product.getId());
            relation.setGoodsAttrId(goodsSpec.getGoodsAttrId());
            relation.setGoodsValueId(goodsSpec.getGoodsValueId());
        }
        goodsProductSpecRelationService.saveBatch(goodsSpecRelations);
    }
}