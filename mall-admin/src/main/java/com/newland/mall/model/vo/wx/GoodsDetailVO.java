package com.newland.mall.model.vo.wx;

import com.newland.mall.entity.*;
import lombok.Data;

import java.util.List;

/**
 * 商品详情
 * Author: leell
 * Date: 2023/4/4 20:09:52
 */
@Data
public class GoodsDetailVO {
    private Goods info ;
    private  List<GoodsAttributeValue> attribute;
    private List<GoodsSpecificationVO> specificationList;
    private List<GoodsProduct> productList;
    private  List<Issue> issue;
    private  Brand brand ;
    private List<CommentVO> comment;
    private  List<GrouponRules> groupon;
    private Boolean share;
    private String shareImage;
}
