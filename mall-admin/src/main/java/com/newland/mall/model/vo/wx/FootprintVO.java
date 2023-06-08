package com.newland.mall.model.vo.wx;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 足迹
 * Author: leell
 * Date: 2023/4/4 19:49:38
 */
@Data
public class FootprintVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long goodsId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private String name;
    private String brief;
    private String picUrl;
    private BigDecimal retailPrice;
}
