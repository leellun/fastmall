package com.newland.mall.model.vo.wx;

import com.newland.mall.entity.Goods;
import com.newland.mall.entity.Groupon;
import lombok.Data;

/**
 * 参加团购信息
 * Author: leell
 * Date: 2023/4/5 10:38:53
 */
@Data
public class GrouponJoinVO {
    private Groupon groupon;
    private Goods goods;
}
