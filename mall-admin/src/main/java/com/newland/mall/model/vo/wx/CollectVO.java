package com.newland.mall.model.vo.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 收藏
 *
 * @author liulun
 * @date 2023/4/4 12:12
 */
@Data
public class CollectVO {
    @JsonSerialize(using= ToStringSerializer.class)
    private Long id;
    private Integer type;
    @JsonSerialize(using= ToStringSerializer.class)
    private Long valueId;
    private String picUrl;

    private String name;
    private String brief;
    private BigDecimal retailPrice;

    private String title;
    private String subtitle;
    private BigDecimal price;
}
