package com.newland.mall.model.vo;

import lombok.Data;

import java.util.List;

/**
 * 品牌 类别
 * Author: leell
 * Date: 2023/3/18 18:16:37
 */
@Data
public class BrandAndCategoryVO {
    private List<CateVO> categoryList;
    private List<BrandVO> brandList;
}
