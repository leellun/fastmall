package com.newland.mall.service.impl;

import com.newland.mall.enums.ConfigTypeEnum;
import com.newland.mall.mapper.ConfigMapper;
import com.newland.mall.service.ConfigService;
import com.newland.mybatis.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.newland.mall.entity.Config;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 系统配置表 服务实现类
 *
 * @author leellun
 * @since 2023-02-27 22:18:25
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {
    @Override
    public List<Config> listMall() {
        return baseMapper.listByType(ConfigTypeEnum.MALL.getKey());
    }

    @Override
    public List<Config> listExpress() {
        return baseMapper.listByType(ConfigTypeEnum.EXPRESS.getKey());
    }

    @Override
    public List<Config> listOrder() {
        return baseMapper.listByType(ConfigTypeEnum.ORDER.getKey());
    }

    @Override
    public List<Config> listWx() {
        return baseMapper.listByType(ConfigTypeEnum.WX.getKey());
    }

    @Override
    public Map<String, String> mapAll() {
        List<Config> systemList = baseMapper.listAll();
        Map<String, String> systemConfigs = new HashMap<>(16);
        for (Config item : systemList) {
            systemConfigs.put(item.getKeyName(), item.getKeyValue());
        }
        return systemConfigs;
    }

    @Override
    public void addConfig(String key, String value) {
        Config config = new Config();
        config.setKeyName(key);
        config.setKeyValue(value);
        config.setCreateTime(LocalDateTime.now());
        config.setUpdateTime(LocalDateTime.now());
        baseMapper.insertSelective(config);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateConfig(Integer type, List<Config> configs) {
        List<Config> dbConfigs = baseMapper.listByType(type);
        Map<String, Config> map = dbConfigs.stream().collect(Collectors.toMap(Config::getKeyName, Function.identity()));
        for (Config config : configs) {
            config.setDeleted(0);
            config.setType(type);
            Config dbConfig = map.remove(config.getKeyName());
            if (dbConfig == null) {
                baseMapper.insert(config);
            } else {
                config.setId(dbConfig.getId());
                baseMapper.updateByPrimaryKeySelective(config);
            }
        }
        for (Config config : map.values()) {
            baseMapper.deleteLogicalByPrimaryKey(config.getId());
        }
    }

    @Override
    public Config getConfig(String key) {
        return baseMapper.getByKey(key);
    }
}