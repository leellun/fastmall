package com.newland.mall.model.dto;

import com.newland.mall.entity.Goods;
import com.newland.mall.entity.GoodsAttrValue;
import com.newland.mall.entity.GoodsProduct;
import com.newland.mall.entity.GoodsSpec;
import com.newland.mall.model.vo.GoodsProductVo;
import com.newland.mall.model.vo.GoodsSaleAttrVo;
import lombok.Data;

import java.util.List;

/**
 * 商品添加
 * @author leell
 */
@Data
public class GoodsAllinoneDTO {
    Goods goods;
    List<GoodsSaleAttrVo> goodsSaleAttrVos;
    List<GoodsAttrValue> attributes;
    List<GoodsProductVo> products;

}
