package com.newland.mall.config;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * long型和type转换
 * Author: leell
 * Date: 2023/4/8 20:53:24
 */
//@Configuration
public class JacksonConfig {
    /**
     * Jackson 全局转化Long 类型为String，解决jackson序列化时Long 类型缺失精度问题
     *
     * @return Jackson20bjectMapperBuilderCustomizer 注入的对象
     */
//    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson20bjectMapperBuilderCustomizer() {
        return jacksonobjectMapperBuilder -> jacksonobjectMapperBuilder
                .serializerByType(Long.class, ToStringSerializer.instance)
                .serializerByType(Long.TYPE, ToStringSerializer.instance);
    }
}
