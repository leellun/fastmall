package com.newland.mall.service.impl;

import com.newland.mall.entity.GoodsSaleAttr;
import com.newland.mall.entity.GoodsSpec;
import com.newland.mall.mapper.GoodsSaleAttrMapper;
import com.newland.mall.mapper.GoodsSpecMapper;
import com.newland.mall.model.vo.GoodsSaleAttrVo;
import com.newland.mall.service.GoodsSaleAttrService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 商品规格表(销售) 服务实现类
 *
 * @author leellun
 * @since 2023-06-11 15:11:54
 */
@Service
public class GoodsSaleAttrServiceImpl extends ServiceImpl<GoodsSaleAttrMapper, GoodsSaleAttr> implements GoodsSaleAttrService {
    @Autowired
    private GoodsSpecMapper goodsSpecMapper;

    @Override
    public List<GoodsSaleAttrVo> saveSaleAttr(Long goodsId, List<GoodsSaleAttrVo> goodsSaleAttrVos) {
        for (GoodsSaleAttrVo goodsSaleAttrVo : goodsSaleAttrVos) {
            goodsSaleAttrVo.setGoodsId(goodsId);
            baseMapper.insertSelective(goodsSaleAttrVo);
            for (GoodsSpec goodsSpec : goodsSaleAttrVo.getGoodsSpecs()) {
                goodsSpec.setGoodsId(goodsId);
                goodsSpec.setSaleAttrId(goodsSaleAttrVo.getId());
                goodsSpecMapper.insertSelective(goodsSpec);
            }
        }
        return goodsSaleAttrVos;
    }

    @Override
    public List<GoodsSaleAttrVo> updateSaleAttr(Long goodsId, List<GoodsSaleAttrVo> goodsSaleAttrVos) {
        List<Long> saleAttrIdsDb = baseMapper.listByGoodsId(goodsId).stream().map(GoodsSaleAttr::getId).collect(Collectors.toList());
        List<Long> specIdsDb = goodsSpecMapper.listByGoodsId(goodsId).stream().map(GoodsSpec::getId).collect(Collectors.toList());
        for (GoodsSaleAttrVo goodsSaleAttrVo : goodsSaleAttrVos) {
            goodsSaleAttrVo.setGoodsId(goodsId);
            if (goodsSaleAttrVo.getId() == null) {
                baseMapper.insertSelective(goodsSaleAttrVo);
            } else {
                baseMapper.updateByPrimaryKeySelective(goodsSaleAttrVo);
                saleAttrIdsDb.remove(goodsSaleAttrVo.getId());
            }
            for (GoodsSpec goodsSpec : goodsSaleAttrVo.getGoodsSpecs()) {
                goodsSpec.setGoodsId(goodsId);
                goodsSpec.setSaleAttrId(goodsSaleAttrVo.getId());
                if (goodsSpec.getId() == null) {
                    goodsSpecMapper.insertSelective(goodsSpec);
                } else {
                    specIdsDb.remove(goodsSpec.getId());
                    goodsSpecMapper.updateByPrimaryKeySelective(goodsSpec);
                }
            }
        }
        if (saleAttrIdsDb.size() > 0) {
            for (Long id : saleAttrIdsDb) {
                baseMapper.deleteByPrimaryKey(id);
            }
        }
        if (specIdsDb.size() > 0) {
            for (Long id : specIdsDb) {
                goodsSpecMapper.deleteByPrimaryKey(id);
            }
        }
        return goodsSaleAttrVos;
    }
}