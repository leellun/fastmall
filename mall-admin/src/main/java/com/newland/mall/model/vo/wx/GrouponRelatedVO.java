package com.newland.mall.model.vo.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.entity.Groupon;
import com.newland.mall.entity.GrouponRules;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 相关团购
 * Author: leell
 * Date: 2023/4/5 10:57:26
 */
@Data
public class GrouponRelatedVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private Groupon groupon;
    private GrouponRules rules;
    private String creator;
    private Boolean isCreator;
    private Integer joinerCount;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;
    private String orderSn;
    private BigDecimal actualPrice;
    private String orderStatusText;
    private List<GrouponOrderGoodsVO> goodsList;

}
