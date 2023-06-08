package com.newland.mall.model.vo.wx;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class GrouponRuleVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private String name;
    private String brief;
    private String picUrl;
    private BigDecimal counterPrice;
    private BigDecimal retailPrice;
    private BigDecimal grouponPrice;
    private BigDecimal grouponDiscount;
    private Integer grouponMember;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expireTime;
}
