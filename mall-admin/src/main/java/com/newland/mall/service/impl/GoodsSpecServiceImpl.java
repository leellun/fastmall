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

/**
 * 商品规格表(销售) 服务实现类
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
}