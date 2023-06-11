package com.newland.mall.service.impl;

import com.newland.mall.entity.GoodsAttrValue;
import com.newland.mall.mapper.GoodsAttrValueMapper;
import com.newland.mall.service.GoodsAttrValueService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品参数表 服务实现类
 *
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Service
public class GoodsAttrValueServiceImpl extends ServiceImpl<GoodsAttrValueMapper, GoodsAttrValue> implements GoodsAttrValueService {
    @Override
    public List<GoodsAttrValue> listGoodsAttributes(Long goodsId) {
        return baseMapper.listByGoodsId(goodsId);
    }

    @Override
    public void add(GoodsAttrValue attribute) {
        baseMapper.insert(attribute);
    }

    @Override
    public void delete(Long id) {
        GoodsAttrValue attribute = new GoodsAttrValue();
        attribute.setId(id);
        attribute.setDeleted(1);
        AssertUtil.isTrue(baseMapper.updateByPrimaryKeySelective(attribute) > 0, "删除失败");
    }

    @Override
    public void saveAttributes(Long goodsId, List<GoodsAttrValue> attributes) {
        // 商品参数表_goods_attribute
        for (GoodsAttrValue attribute : attributes) {
            attribute.setGoodsId(goodsId);
            baseMapper.insertSelective(attribute);
        }
    }

    @Override
    public void updateAttributes(Long goodsId, List<GoodsAttrValue> attributes) {
        List<Long> attributeIdsDb = baseMapper.listByGoodsId(goodsId).stream().map(GoodsAttrValue::getId).collect(Collectors.toList());
        List<Long> attributeIdList = new ArrayList<>();
        // 商品参数表_goods_attribute
        for (GoodsAttrValue attribute : attributes) {
            if (attribute.getId() != null && attributeIdsDb.contains(attribute.getId())) {
                attributeIdList.add(attribute.getId());
                baseMapper.updateByPrimaryKeySelective(attribute);
            } else {
                attribute.setGoodsId(goodsId);
                baseMapper.insertSelective(attribute);
            }
        }
        attributeIdsDb.removeAll(attributeIdList);

        List<GoodsAttrValue> deleteAttrValues = attributeIdsDb.stream().map(id -> {
            GoodsAttrValue attrValue = new GoodsAttrValue();
            attrValue.setId(id);
            attrValue.setDeleted(1);
            return attrValue;
        }).collect(Collectors.toList());
        this.updateBatch(deleteAttrValues);
    }
}