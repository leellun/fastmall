package com.newland.mall.service.impl;

import com.github.pagehelper.PageInfo;
import com.newland.mall.entity.GoodsAttr;
import com.newland.mall.mapper.GoodsAttrMapper;
import com.newland.mall.mapper.GoodsGroupAttrRelationMapper;
import com.newland.mall.mapper.GoodsValueMapper;
import com.newland.mall.model.dto.GoodsAttributeDto;
import com.newland.mall.model.vo.GoodsAttrInfoVo;
import com.newland.mall.model.vo.GoodsAttrWithValueVo;
import com.newland.mall.service.GoodsAttrService;
import com.newland.mybatis.page.PageEntity;
import com.newland.mybatis.page.PageWrapper;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品属性参数表 服务实现类
 *
 * @author leellun
 * @since 2023-06-08 13:44:15
 */
@Service
public class GoodsAttrServiceImpl extends ServiceImpl<GoodsAttrMapper, GoodsAttr> implements GoodsAttrService {
    @Autowired
    private GoodsGroupAttrRelationMapper goodsGroupAttrRelationMapper;
    @Autowired
    private GoodsValueMapper goodsValueMapper;

    @Override
    public PageInfo<GoodsAttr> getList(Integer type, Integer pageSize, Integer pageNo) {
        return PageWrapper.page(PageEntity.page(pageNo, pageSize), () -> baseMapper.listByType(type));
    }

    @Override
    public List<GoodsAttr> getListAttr(Long gid, Integer type) {
        return baseMapper.listByGidAndType(gid, type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(GoodsAttributeDto productAttributeDto) {
        GoodsAttr productAttribute = new GoodsAttr();
        BeanUtils.copyProperties(productAttributeDto, productAttribute);
        baseMapper.insert(productAttribute);
        //新增商品属性以后需要更新商品属性分类数量
//        GoodsGroup productAttributeCategory = goodsGroupMapper.selectByPrimaryKey(productAttribute.getGoodsAttributeCategoryId());
//        if (productAttribute.getType() == 0) {
//            productAttributeCategory.setAttributeCount(productAttributeCategory.getAttributeCount() + 1);
//        } else if (productAttribute.getType() == 1) {
//            productAttributeCategory.setParamCount(productAttributeCategory.getParamCount() + 1);
//        }
//        goodsGroupMapper.updateByPrimaryKeySelective(productAttributeCategory);
    }

    @Override
    public void update(Long id, GoodsAttributeDto productAttributeParam) {
        GoodsAttr productAttribute = new GoodsAttr();
        productAttribute.setId(id);
        BeanUtils.copyProperties(productAttributeParam, productAttribute);
        baseMapper.updateByPrimaryKeySelective(productAttribute);
    }

    @Override
    public GoodsAttr getItem(Long id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(List<Long> ids) {
        //获取分类
        GoodsAttr productAttribute = baseMapper.selectByPrimaryKey(ids.get(0));
        Integer type = productAttribute.getType();
        int count = baseMapper.deleteBatchIds(ids);
        goodsGroupAttrRelationMapper.deleteByAttrIds(ids);
//        GoodsGroup productAttributeCategory = goodsGroupMapper.selectByPrimaryKey(productAttribute.getGoodsAttributeCategoryId());
//        //删除完成后修改数量
//        if (type == 0) {
//            if (productAttributeCategory.getAttributeCount() >= count) {
//                productAttributeCategory.setAttributeCount(productAttributeCategory.getAttributeCount() - count);
//            } else {
//                productAttributeCategory.setAttributeCount(0);
//            }
//        } else if (type == 1) {
//            if (productAttributeCategory.getParamCount() >= count) {
//                productAttributeCategory.setParamCount(productAttributeCategory.getParamCount() - count);
//            } else {
//                productAttributeCategory.setParamCount(0);
//            }
//        }
//        goodsGroupMapper.updateByPrimaryKeySelective(productAttributeCategory);
        return count;
    }

    @Override
    public List<GoodsAttrInfoVo> getGoodsAttrInfo(Long goodsCategoryId) {
        return baseMapper.getGoodsAttrInfo(goodsCategoryId);
    }

    @Override
    public PageInfo<GoodsAttr> getUnBindListAttr(Long gid, Integer type, Integer pageNo, Integer pageSize) {
        return PageWrapper.page(PageEntity.page(pageNo, pageSize), () -> baseMapper.listUnBindByGidAndType(gid, type));
    }

    @Override
    public List<GoodsAttrWithValueVo> getBindsWithValue(Long gid, Integer type) {
        List<GoodsAttr> attrs = this.getListAttr(gid, type);
        List<GoodsAttrWithValueVo> list = new ArrayList<>(attrs.size());
        for (GoodsAttr attr : attrs) {
            GoodsAttrWithValueVo vo = new GoodsAttrWithValueVo();
            BeanUtils.copyProperties(attr, vo);
            if (attr.getInputType() == 1) {
                vo.setValues(goodsValueMapper.listByAttrId(attr.getId()));
            }
            list.add(vo);
        }
        return list;
    }
}