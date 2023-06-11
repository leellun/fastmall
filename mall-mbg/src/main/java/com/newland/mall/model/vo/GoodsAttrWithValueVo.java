package com.newland.mall.model.vo;

import com.newland.mall.entity.GoodsAttr;
import com.newland.mall.entity.GoodsValue;
import lombok.Data;

import java.util.List;

/**
 * 带值列表的属性
 * @author leell
 * @date 2023/6/11 18:41:31
 */
@Data
public class GoodsAttrWithValueVo extends GoodsAttr {
    private List<GoodsValue> values;
}
