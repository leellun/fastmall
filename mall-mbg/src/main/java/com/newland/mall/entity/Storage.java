package com.newland.mall.entity;

import com.newland.mall.model.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件存储表
 * mall_storage
 * @author leell
 * @date 2023-02-27 22:03:47
 */
@Data
@Schema(name ="文件存储表")
public class Storage  extends BaseEntity implements Serializable {

    /**
     * 文件的唯一索引
     */
    @Schema(name ="文件的唯一索引")
    private String uniqueKey;

    /**
     * 文件名
     */
    @Schema(name ="文件名")
    private String name;

    /**
     * 文件类型
     */
    @Schema(name ="文件类型")
    private String type;

    /**
     * 文件大小
     */
    @Schema(name ="文件大小")
    private Integer size;

    /**
     * 文件访问链接
     */
    @Schema(name ="文件访问链接")
    private String url;


    private static final long serialVersionUID = 1L;
}