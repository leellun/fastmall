package com.newland.mall.service;

import com.newland.mall.entity.Log;
import com.newland.mall.model.LoginUser;
import com.newland.mall.utils.IpUtil;
import com.newland.security.utils.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

/**
 * 这里的日志类型设计成四种（当然开发者需要可以自己扩展）
 * 一般日志：用户觉得需要查看的一般操作日志，建议是默认的日志级别
 * 安全日志：用户安全相关的操作日志，例如登录、删除管理员
 * 订单日志：用户交易相关的操作日志，例如订单发货、退款
 * 其他日志：如果以上三种不合适，可以选择其他日志，建议是优先级最低的日志级别
 * <p>
 * 当然可能很多操作是不需要记录到数据库的，例如编辑商品、编辑广告品之类。
 */
@Component
public class LogHelper {
    public static final Integer LOG_TYPE_GENERAL = 0;
    public static final Integer LOG_TYPE_AUTH = 1;
    public static final Integer LOG_TYPE_ORDER = 2;
    public static final Integer LOG_TYPE_OTHER = 3;

    @Autowired
    private LogService logService;

    public void logGeneralSucceed(String action) {
        logAdmin(LOG_TYPE_GENERAL, action, 1, "", "");
    }

    public void logGeneralSucceed(String action, String result) {
        logAdmin(LOG_TYPE_GENERAL, action, 1, result, "");
    }

    public void logGeneralFail(String action, String error) {
        logAdmin(LOG_TYPE_GENERAL, action, 0, error, "");
    }

    public void logAuthSucceed(String action) {
        logAdmin(LOG_TYPE_AUTH, action, 1, "", "");
    }

    public void logAuthSucceed(String action, String result) {
        logAdmin(LOG_TYPE_AUTH, action, 1, result, "");
    }

    public void logAuthFail(String action, String error) {
        logAdmin(LOG_TYPE_AUTH, action, 0, error, "");
    }

    public void logOrderSucceed(String action) {
        logAdmin(LOG_TYPE_ORDER, action, 1, "", "");
    }

    public void logOrderSucceed(String action, String result) {
        logAdmin(LOG_TYPE_ORDER, action, 1, result, "");
    }

    public void logOrderFail(String action, String error) {
        logAdmin(LOG_TYPE_ORDER, action, 0, error, "");
    }

    public void logOtherSucceed(String action) {
        logAdmin(LOG_TYPE_OTHER, action, 1, "", "");
    }

    public void logOtherSucceed(String action, String result) {
        logAdmin(LOG_TYPE_OTHER, action, 1, result, "");
    }


    public void logOtherFail(String action, String error) {
        logAdmin(LOG_TYPE_OTHER, action, 0, error, "");
    }

    public void logAdmin(Integer type, String action, Integer succeed, String result, String comment) {
        Log log = new Log();

        LoginUser currentUser = SecurityUtil.getLoginUser();
        if (currentUser != null) {
            log.setAdmin(currentUser.getUsername());
        } else {
            log.setAdmin("匿名用户");
        }

        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request != null) {
            log.setIp(IpUtil.getIpAddr(request));
        }

        log.setType(type);
        log.setAction(action);
        log.setStatus(succeed);
        log.setResult(result);
        log.setComment(comment);
        logService.saveBatch(Arrays.asList(log));
    }

}
