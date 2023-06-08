package com.newland.mall.model.vo.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.entity.Groupon;
import com.newland.mall.entity.GrouponRules;
import com.newland.mall.express.dao.ExpressInfo;
import com.newland.mall.model.vo.UserVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 团购详情
 * Author: leell
 * Date: 2023/4/5 09:38:56
 */
@Data
@Schema(name = "团购详情")
public class GrouponDetailVO {
    @Schema(name = "当前用户团购订单信息")
    private GrouponOrderVO orderInfo;
    @Schema(name = "团购商品")
    private List<GrouponOrderGoodsVO> orderGoods;
    @Schema(name = "物流追踪信息")
    private ExpressInfo expressInfo;
    @Schema(name = "团购活动id")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long linkGrouponId;
    @Schema(name = "团购创建者信息")
    private UserVO creator;
    @Schema(name = "参与者信息")
    private List<UserVO> joiners;
    @Schema(name = "团购活动")
    private Groupon groupon;
    @Schema(name = "团购规则")
    private GrouponRules rules;
}
