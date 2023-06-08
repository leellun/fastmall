package com.newland.mall.express.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Data
@ConfigurationProperties(prefix = "mall.express")
public class ExpressProperties {
    private boolean enable;
    private String appId;
    private String appKey;
    private List<Map<String, String>> vendors = new ArrayList<>();
}
