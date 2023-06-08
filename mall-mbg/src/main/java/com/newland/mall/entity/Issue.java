package com.newland.mall.entity;

import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 常见问题表
 * mall_issue
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="常见问题表")
public class Issue  extends BaseEntity implements Serializable {

    /**
     * 问题标题
     */
    @Schema(name ="问题标题")
    private String question;

    /**
     * 问题答案
     */
    @Schema(name ="问题答案")
    private String answer;

    private static final long serialVersionUID = 1L;
}