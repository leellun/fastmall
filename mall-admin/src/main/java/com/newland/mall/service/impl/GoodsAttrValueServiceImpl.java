package com.newland.mall.service.impl;

import com.newland.mall.entity.GoodsAttrValue;
import com.newland.mall.mapper.GoodsAttrValueMapper;
import com.newland.mall.service.GoodsAttrValueService;
import com.newland.mall.utils.AssertUtil;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品参数表 服务实现类
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
}