package com.newland.mall.service.impl;

import com.newland.mall.entity.GoodsAttributeValue;
import com.newland.mall.mapper.GoodsAttributeValueMapper;
import com.newland.mall.service.GoodsAttributeValueService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品参数表 服务实现类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class GoodsAttributeValueServiceImpl extends ServiceImpl<GoodsAttributeValueMapper, GoodsAttributeValue> implements GoodsAttributeValueService {
    @Override
    public List<GoodsAttributeValue> listGoodsAttributes(Long goodsId) {
        return baseMapper.listByGoodsId(goodsId);
    }

    @Override
    public void add(GoodsAttributeValue attribute) {
        baseMapper.insert(attribute);
    }

    @Override
    public void delete(Long id) {
        GoodsAttributeValue attribute = new GoodsAttributeValue();
        attribute.setId(id);
        attribute.setDeleted(1);
        AssertUtil.isTrue(baseMapper.updateByPrimaryKeySelective(attribute) > 0, "删除失败");
    }
}