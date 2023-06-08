package com.newland.mall.mapper;

import com.newland.mall.model.vo.StatGoodsVO;
import com.newland.mall.model.vo.StatOrderVO;
import com.newland.mall.model.vo.StatUserVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StatMapper {
    List<StatUserVO> statUser();

    List<StatOrderVO> statOrder();

    List<StatGoodsVO> statGoods();
}