package com.newland.mall.service.impl;

import com.newland.mall.entity.GoodsSpec;
import com.newland.mall.mapper.GoodsSpecMapper;
import com.newland.mall.model.vo.wx.GoodsSpecificationVO;
import com.newland.mall.service.GoodsSpecService;
import com.newland.mybatis.service.impl.ServiceImpl;
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
 * @since 2023-06-08 13:44:15
 */
@Service
public class GoodsSpecServiceImpl extends ServiceImpl<GoodsSpecMapper, GoodsSpec> implements GoodsSpecService {
    @Override
    public List<GoodsSpec> listGoodsSpecifications(Long goodsId) {
        return baseMapper.listByGoodsId(goodsId);
    }

    @Override
    public void add(GoodsSpec specification) {
        baseMapper.insert(specification);
    }

    @Override
    public List<GoodsSpecificationVO> getSpecificationVoList(Long goodsId) {
        List<GoodsSpec> goodsSpecifications = this.listGoodsSpecifications(goodsId);
        Map<String, GoodsSpecificationVO> map = new HashMap<>();
        List<GoodsSpecificationVO> goodsSpecificationVOS = new ArrayList<>();
        for (GoodsSpec goodsSpecification : goodsSpecifications) {
//            GoodsSpecificationVO vo = map.get(goodsSpecification.getSpecification());
//            if (vo == null) {
//                vo = new GoodsSpecificationVO();
//                map.put(goodsSpecification.getSpecification(), vo);
//                vo.setName(goodsSpecification.getSpecification());
//                List<GoodsSpecification> valueList = new ArrayList<>();
//                valueList.add(goodsSpecification);
//                vo.setValueList(valueList);
//                goodsSpecificationVOS.add(vo);
//            } else {
//                vo.getValueList().add(goodsSpecification);
//            }
        }
        return goodsSpecificationVOS;
    }

    @Override
    public Map<String, GoodsSpec> saveGoodsSpecs(Long goodsId, List<GoodsSpec> specifications) {
        Map<String, GoodsSpec> specMap = new HashMap<>(specifications.size());
        // 商品规格表_goods_specification
        for (GoodsSpec specification : specifications) {
            specification.setGoodsId(goodsId);
            baseMapper.insertSelective(specification);
            specMap.put(specification.getName() + "=" + specification.getValue(), specification);
        }
        return specMap;
    }

    @Override
    public Map<String, GoodsSpec> updateGoodsSpecs(Long goodsId, List<GoodsSpec> specifications) {
        List<Long> specIdsDb = baseMapper.listByGoodsId(goodsId).stream().map(GoodsSpec::getId).collect(Collectors.toList());
        List<Long> specIds = new ArrayList<>();
        Map<String, GoodsSpec> specMap = new HashMap<>(specifications.size());
        // 商品规格表_goods_specification
        for (GoodsSpec specification : specifications) {
            if (specification.getId() != null && specIdsDb.contains(specification.getId())) {
                specIds.add(specification.getId());
                baseMapper.updateByPrimaryKeySelective(specification);
            } else {
                specification.setGoodsId(goodsId);
                baseMapper.insertSelective(specification);
            }
            specMap.put(specification.getName() + "=" + specification.getValue(), specification);
        }
        specIdsDb.removeAll(specIds);
        List<GoodsSpec> deleteItems = specIdsDb.stream().map(id -> {
            GoodsSpec goodsSpec = new GoodsSpec();
            goodsSpec.setId(id);
            goodsSpec.setDeleted(1);
            return goodsSpec;
        }).collect(Collectors.toList());
        this.updateBatch(deleteItems);
        return specMap;
    }
}