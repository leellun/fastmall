package com.newland.mall.model.vo.wx;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 评论
 *
 * @author liulun
 * @date 2023/4/4 16:11
 */
@Data
public class CommentVO {
    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 评论内容
     */
    @Schema(name = "评论内容")
    private String content;
    /**
     * 管理员回复内容
     */
    @Schema(name ="管理员回复内容")
    private String adminContent;

    /**
     * 图片地址列表，采用JSON数组格式
     */
    @Schema(name = "图片地址列表，采用JSON数组格式")
    private String[] picUrls;

    /**
     * 评分， 1-5
     */
    @Schema(name = "评分， 1-5")
    private Integer star;
    @Schema(name = "用户信息")
    private UserInfoVO userInfo;
}
