package com.newland.mall.service;

import com.newland.mall.model.vo.StatGoodsVO;
import com.newland.mall.model.vo.StatOrderVO;
import com.newland.mall.model.vo.StatUserVO;

import java.util.List;

/**
 * 统计信息
 */
public interface StatService {
    public List<StatUserVO> statUser() ;

    public List<StatOrderVO> statOrder() ;

    public List<StatGoodsVO> statGoods();
}
