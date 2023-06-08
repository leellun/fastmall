package com.newland.mall.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderVO {
    private Integer id;
    private String orderSn;
    private Integer orderStatus;
    private BigDecimal actualPrice;
    private BigDecimal integralPrice;
    private BigDecimal freightPrice;
    private BigDecimal orderPrice;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private Integer userId;
    private String userName;
    private String userAvatar;
    private String consignee;
    private String address;
    private String mobile;
    private String shipChannel;
    private String shipSn;
    private String message;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;
    private List<OrderGoodsVO> goodsVoList;
}
