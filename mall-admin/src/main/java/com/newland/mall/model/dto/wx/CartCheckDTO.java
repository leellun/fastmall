package com.newland.mall.model.dto.wx;

import com.newland.mall.validator.IntOptions;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

/**
 * 购物车选中
 * @author liulun
 * @date 2023/4/4 10:53
 */
@Data
public class CartCheckDTO {
    @NotEmpty(message = "请选中购买商品")
    private List<Long> productIds;
    @NotEmpty(message = "选中状态不能为空")
    @IntOptions(value = {0,1},message = "选中状态不正确")
    private Integer isChecked;
}
