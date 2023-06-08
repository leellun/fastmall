package com.newland.mall.model.dto;

import com.newland.mybatis.page.PageEntity;
import lombok.Data;

/**
 * 地址查询
 * Date: 2023/3/18 00:51:24
 * @author leell
 */
@Data
public class AddressQueryDTO extends PageEntity {
    Integer userId;
    String name;
}
