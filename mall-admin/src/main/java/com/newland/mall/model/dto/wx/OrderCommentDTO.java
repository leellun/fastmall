package com.newland.mall.model.dto.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.List;

/**
 * 订单评价
 * Author: leell
 * Date: 2023/4/5 18:10:38
 */
@Data
public class OrderCommentDTO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderId;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long orderGoodsId;
    private String content;
    @NotNull(message = "星参数不正确")
    @Range(min = 1,max = 5,message = "评星1-5")
    private Integer star;
    private Integer hasPicture;
    private List<String> picUrls;
}
