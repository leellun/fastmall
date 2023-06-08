package com.newland.mall.model.vo.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.entity.Goods;
import lombok.Data;

import java.util.List;

/**
 * 分类商品
 * @author liulun
 * @date 2023/4/6 17:06
 */
@Data
public class CategoryGoodsVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String name;
    private List<Goods> goodsList;
}
