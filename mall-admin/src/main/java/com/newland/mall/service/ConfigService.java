package com.newland.mall.service;

import com.newland.mybatis.service.IService;
import com.newland.mall.entity.Config;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 系统配置表 服务类
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
public interface ConfigService extends IService<Config> {
    /**
     * 商城配置列表
     * @return
     */
    List<Config> listMall();

    /**
     * 更新配置
     * @param configs
     */
    void updateConfig(Integer type,List<Config> configs);

    /**
     * 运费配置列表
     * @return
     */

    List<Config> listExpress();

    /**
     * 订单配置列表
     * @return
     */

    List<Config> listOrder();

    /**
     * 小程序配置详情
     * @return
     */
    List<Config> listWx();

    /**
     * 所有配置
     * @return
     */
    Map<String, String> mapAll();

    /**
     * 添加配置
     * @param key
     * @param value
     */
    void addConfig(String key, String value);

    /**
     * 通过key获取配置
     * @param key
     * @return
     */
    Config getConfig(String key);
}