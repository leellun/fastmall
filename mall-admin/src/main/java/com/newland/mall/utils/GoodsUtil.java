package com.newland.mall.utils;

import com.newland.mall.entity.GoodsSpec;
import com.newland.mall.model.vo.GoodsSaleAttrVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品工具类
 *
 * @author leell
 * @date 2023/6/11 17:34:26
 */
public class GoodsUtil {
    public static String key(String name, String value) {
        return name + "=" + value;
    }

    public static Map<String, GoodsSpec> getGoodsSpecMap(List<GoodsSaleAttrVo> goodsSaleAttrVos) {
        Map<String, GoodsSpec> specMap = new HashMap<>(16);
        for (GoodsSaleAttrVo goodsSaleAttrVo : goodsSaleAttrVos) {
            for (GoodsSpec goodsSpec : goodsSaleAttrVo.getGoodsSpecs()) {
                specMap.put(goodsSpec.key(), goodsSpec);
            }
        }
        return specMap;
    }
}
