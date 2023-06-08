package com.newland.mall.entity;

import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 专题表
 * mall_topic
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="专题表")
public class Topic  extends BaseEntity implements Serializable {

    /**
     * 专题标题
     */
    @Schema(name ="专题标题")
    private String title;

    /**
     * 专题子标题
     */
    @Schema(name ="专题子标题")
    private String subtitle;

    /**
     * 专题相关商品最低价
     */
    @Schema(name ="专题相关商品最低价")
    private BigDecimal price;

    /**
     * 专题阅读量
     */
    @Schema(name ="专题阅读量")
    private String readCount;

    /**
     * 专题图片
     */
    @Schema(name ="专题图片")
    private String picUrl;

    /**
     * 排序
     */
    @Schema(name ="排序")
    private Integer sortOrder;

    /**
     * 专题相关商品，采用JSON数组格式
     */
    @Schema(name ="专题相关商品，采用JSON数组格式")
    private Long[] goods;


    /**
     * 专题内容，富文本格式
     */
    @Schema(name ="专题内容，富文本格式")
    private String content;

    private static final long serialVersionUID = 1L;
}