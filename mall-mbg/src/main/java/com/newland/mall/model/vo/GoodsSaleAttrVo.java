package com.newland.mall.model.vo;

import com.newland.mall.entity.GoodsSaleAttr;
import com.newland.mall.entity.GoodsSpec;
import lombok.Data;

import java.util.List;

/**
 * 销售属性
 * @author leell
 * @date 2023/6/11 15:16:14
 */
@Data
public class GoodsSaleAttrVo extends GoodsSaleAttr {
    private List<GoodsSpec> goodsSpecs;
}

