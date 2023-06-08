package com.newland.mall.model.vo.wx;

import lombok.Data;

/**
 * 所有评价和有图评价
 * @author liulun
 * @date 2023/4/4 14:17
 */
@Data
public class CommentCountVO {
    private Long allCount;
    private Long hasPicCount;
}
