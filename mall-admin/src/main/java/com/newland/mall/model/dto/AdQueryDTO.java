package com.newland.mall.model.dto;

import com.newland.mybatis.page.PageEntity;
import lombok.Data;

/**
 * 广告后台查询
 * Author: leell
 * Date: 2023/3/18 00:40:00
 */
@Data
public class AdQueryDTO extends PageEntity {
    /**
     * 名称
     */
    String name;
    /**
     * 内容
     */
    String content;
}
