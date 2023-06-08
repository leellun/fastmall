package com.newland.mall.model.vo;

import com.newland.mall.entity.Goods;
import com.newland.mall.entity.Topic;
import lombok.Data;

import java.util.List;

/**
 * 主题详情
 * Author: leell
 * Date: 2023/3/20 00:29:15
 */
@Data
public class TopicVO {
    private Topic topic;
    private List<Goods> goodsList;
}
