package com.newland.mall.model.vo.wx;

import com.newland.mall.entity.GoodsSpec;
import lombok.Data;

import java.util.List;

/**
 * 规格vo
 * Author: leell
 * Date: 2023/4/4 21:14:32
 */
@Data
public class GoodsSpecificationVO {
    private String name;
    private List<GoodsSpec> valueList;
}
