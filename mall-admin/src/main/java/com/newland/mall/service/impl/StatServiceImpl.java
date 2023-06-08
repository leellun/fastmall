package com.newland.mall.service.impl;

import com.newland.mall.mapper.StatMapper;
import com.newland.mall.model.vo.StatGoodsVO;
import com.newland.mall.model.vo.StatOrderVO;
import com.newland.mall.model.vo.StatUserVO;
import com.newland.mall.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Author: leell
 * Date: 2023/3/19 23:52:03
 */
@Service
public class StatServiceImpl implements StatService {
    @Autowired
    private StatMapper statMapper;

    @Override
    public List<StatUserVO> statUser() {
        return statMapper.statUser();
    }

    @Override
    public List<StatOrderVO> statOrder() {
        return statMapper.statOrder();
    }

    @Override
    public List<StatGoodsVO> statGoods() {
        return statMapper.statGoods();
    }
}
