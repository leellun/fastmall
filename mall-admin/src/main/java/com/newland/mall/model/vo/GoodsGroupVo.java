package com.newland.mall.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.newland.mall.entity.GoodsGroup;
import com.newland.mall.jackson.LongListSerializer;
import lombok.Data;

import java.util.List;

/**
 * 属性分组
 * @author leell
 * @date 2023/6/8 23:02:40
 */
@Data
public class GoodsGroupVo {
    /**
     * 属性分组
     */
    private GoodsGroup goodsGroup;

    /**
     * 属性分组对应类id，父类-子类
     */
    @JsonSerialize(using = LongListSerializer.class)
    List<Long> categoryIds;
}
