package com.newland.mall.service.impl;

import com.newland.mall.entity.GoodsSpecification;
import com.newland.mall.mapper.GoodsSpecificationMapper;
import com.newland.mall.model.vo.wx.GoodsSpecificationVO;
import com.newland.mall.service.GoodsSpecificationService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品规格表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class GoodsSpecificationServiceImpl extends ServiceImpl<GoodsSpecificationMapper, GoodsSpecification> implements GoodsSpecificationService {
    @Override
    public List<GoodsSpecification> listGoodsSpecifications(Long goodsId) {
        return baseMapper.listByGoodsId(goodsId);
    }

    @Override
    public void add(GoodsSpecification specification) {
        baseMapper.insert(specification);
    }

    @Override
    public List<GoodsSpecificationVO> getSpecificationVoList(Long goodsId) {
        List<GoodsSpecification> goodsSpecifications = this.listGoodsSpecifications(goodsId);
        Map<String, GoodsSpecificationVO> map = new HashMap<>();
        List<GoodsSpecificationVO> goodsSpecificationVOS = new ArrayList<>();
        for (GoodsSpecification goodsSpecification : goodsSpecifications) {
            GoodsSpecificationVO vo = map.get(goodsSpecification.getSpecification());
            if (vo == null) {
                vo = new GoodsSpecificationVO();
                map.put(goodsSpecification.getSpecification(), vo);
                vo.setName(goodsSpecification.getSpecification());
                List<GoodsSpecification> valueList = new ArrayList<>();
                valueList.add(goodsSpecification);
                vo.setValueList(valueList);
                goodsSpecificationVOS.add(vo);
            } else {
                vo.getValueList().add(goodsSpecification);
            }
        }
        return goodsSpecificationVOS;
    }
}