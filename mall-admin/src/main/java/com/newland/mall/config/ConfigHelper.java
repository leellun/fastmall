package com.newland.mall.config;

import com.newland.mall.entity.Config;
import com.newland.mall.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

import static com.newland.mall.config.SystemConfig.*;

/**
 * 配置辅助类
 * Author: leell
 * Date: 2023/4/4 00:11:04
 */
@Component
public class ConfigHelper {
    private ConcurrentHashMap<String, String> systemConfigs = new ConcurrentHashMap<>();
    @Autowired
    private ConfigService configService;

    private String getConfig(String keyName) {
        String value = systemConfigs.get(keyName);
        if (value == null) {
            Config config = configService.getConfig(keyName);
            value = config == null ? "" : config.getKeyValue();
            systemConfigs.put(keyName, value);
        }
        return value;
    }

    private Integer getConfigInt(String keyName) {
        return Integer.parseInt(getConfig(keyName));
    }

    private Boolean getConfigBoolean(String keyName) {
        return Boolean.valueOf(getConfig(keyName));
    }

    private BigDecimal getConfigBigDec(String keyName) {
        return new BigDecimal(getConfig(keyName));
    }

    public Integer getNewLimit() {
        return getConfigInt(MALL_WX_INDEX_NEW);
    }

    public Integer getHotLimit() {
        return getConfigInt(MALL_WX_INDEX_HOT);
    }

    public Integer getBrandLimit() {
        return getConfigInt(MALL_WX_INDEX_BRAND);
    }

    public Integer getTopicLimit() {
        return getConfigInt(MALL_WX_INDEX_TOPIC);
    }

    public Integer getCatlogListLimit() {
        return getConfigInt(MALL_WX_INDEX_CATLOG_LIST);
    }

    public Integer getCatlogMoreLimit() {
        return getConfigInt(MALL_WX_INDEX_CATLOG_GOODS);
    }

    public boolean isAutoCreateShareImage() {
        return getConfigBoolean(MALL_WX_SHARE);
    }

    public BigDecimal getFreight() {
        return getConfigBigDec(MALL_EXPRESS_FREIGHT_VALUE);
    }

    public BigDecimal getFreightLimit() {
        return getConfigBigDec(MALL_EXPRESS_FREIGHT_MIN);
    }

    public Integer getOrderUnpaid() {
        return getConfigInt(MALL_ORDER_UNPAID);
    }

    public Integer getOrderUnconfirm() {
        return getConfigInt(MALL_ORDER_UNCONFIRM);
    }

    public Integer getOrderComment() {
        return getConfigInt(MALL_ORDER_COMMENT);
    }

    public String getMallName() {
        return getConfig(MALL_MALL_NAME);
    }

    public String getMallAddress() {
        return getConfig(MALL_MALL_ADDRESS);
    }

    public String getMallPhone() {
        return getConfig(MALL_MALL_PHONE);
    }

    public String getMallQQ() {
        return getConfig(MALL_MALL_QQ);
    }

    public String getMallLongitude() {
        return getConfig(MALL_MALL_LONGITUDE);
    }

    public String getMallLatitude() {
        return getConfig(MALL_MALL_Latitude);
    }
}
