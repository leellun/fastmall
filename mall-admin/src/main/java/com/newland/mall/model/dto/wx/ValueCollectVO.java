package com.newland.mall.model.dto.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.enums.CollectTypeEnum;
import com.newland.mall.validator.EnumInteger;
import com.newland.mall.validator.IntOptions;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 商品或主题收藏
 *
 * @author liulun
 * @date 2023/4/4 12:25
 */
@Data
public class ValueCollectVO {
    @NotNull(message = "收藏值不能为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long valueId;
    @NotNull(message = "收藏类型不正确")
    @EnumInteger(enumClass = CollectTypeEnum.class, message = "收藏类型不正确")
    private Integer type;
}
