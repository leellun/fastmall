package com.newland.mall.model.vo;

import com.newland.mall.entity.Goods;
import com.newland.mall.entity.Groupon;
import com.newland.mall.entity.GrouponRules;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 团购列表信息
 * Author: leell
 * Date: 2023/3/19 10:41:31
 */
@Schema(name="团购")
@Data
public class GrouponListItemVO {
    @Schema(name="团购")
    private Groupon groupon;
    @Schema(name="子团购")
    private List<Groupon> subGroupons;
    /**
     * 团购规则
     */
    @Schema(name="团购规则")
    private GrouponRules rules;
    @Schema(name="团购商品")
    private Goods goods;
}
