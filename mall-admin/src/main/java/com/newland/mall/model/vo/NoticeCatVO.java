package com.newland.mall.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 通知查看
 * Author: leell
 * Date: 2023/3/19 22:34:27
 */
@Data
public class NoticeCatVO {
    private String title;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
    private String admin;
    private String avatar;
}
