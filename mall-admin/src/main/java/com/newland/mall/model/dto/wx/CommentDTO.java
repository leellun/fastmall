package com.newland.mall.model.dto.wx;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.newland.mall.enums.CommentTypeEnum;
import com.newland.mall.model.BaseEntity;
import com.newland.mall.validator.EnumInteger;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * 评论
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="评论")
public class CommentDTO implements Serializable {
    /**
     * 如果type=0，则是商品评论；如果是type=1，则是专题评论。
     */
    @Schema(name ="如果type=0，则是商品评论；如果是type=1，则是专题评论。")
    @NotNull(message = "评论对象不能为空")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long valueId;

    /**
     * 评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；
     */
    @Schema(name ="评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；")
    @EnumInteger(enumClass = CommentTypeEnum.class,message = "评论类型不正确")
    private Integer type;

    /**
     * 评论内容
     */
    @Schema(name ="评论内容")
    @NotEmpty(message = "评论内容不能为空")
    private String content;

    /**
     * 是否含有图片
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
    @NotNull(message = "评分不能为空")
    @Range(min = 1,max = 5,message = "评分1-5")
    @Schema(name ="评分， 1-5")
    private Integer star;

    private static final long serialVersionUID = 1L;
}