package com.newland.mall.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderGoodsVO {
    private Integer id;
    private String goodsName;
    private String picUrl;
    private Integer goodsId;
    private Integer productId;
    private String specifications;
    private Integer number;
    private BigDecimal price;
    private String location;
}
