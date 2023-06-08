package com.newland.mall.model.vo;

import com.newland.mall.entity.Notice;
import com.newland.mall.entity.NoticeAdmin;
import lombok.Data;

import java.util.List;

/**
 * 通知
 * Author: leell
 * Date: 2023/3/19 16:21:54
 */
@Data
public class NoticeVO {
    private Notice notice;
    private List<NoticeAdmin> noticeAdminList;
}
