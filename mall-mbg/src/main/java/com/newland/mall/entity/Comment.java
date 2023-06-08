package com.newland.mall.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 评论表
 * mall_comment
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="评论表")
public class Comment extends BaseEntity implements Serializable {

    /**
     * 如果type=0，则是商品评论；如果是type=1，则是专题评论。
     */
    @Schema(name ="如果type=0，则是商品评论；如果是type=1，则是专题评论。")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long valueId;

    /**
     * 评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；
     * @see com.newland.mall.enums.CommentTypeEnum
     */
    @Schema(name ="评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；")
    private Integer type;

    /**
     * 评论内容
     */
    @Schema(name ="评论内容")
    private String content;

    /**
     * 管理员回复内容
     */
    @Schema(name ="管理员回复内容")
    private String adminContent;

    /**
     * 用户表的用户ID
     */
    @Schema(name ="用户表的用户ID")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     * 是否含有图片
     * @see com.newland.mall.enumeration.BasicEnum
     */
    @Schema(name ="是否含有图片")
    private Integer hasPicture;

    /**
     * 图片地址列表，采用JSON数组格式
     */
    @Schema(name ="图片地址列表，采用JSON数组格式")
    private String[] picUrls;

    /**
     * 评分， 1-5
     */
    @Schema(name ="评分， 1-5")
    private Integer star;

    private static final long serialVersionUID = 1L;
}