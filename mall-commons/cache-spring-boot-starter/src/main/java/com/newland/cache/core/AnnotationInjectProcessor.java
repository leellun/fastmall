package com.newland.cache.core;

import com.alibaba.fastjson2.JSON;
import com.newland.cache.annotation.CacheExpire;
import com.newland.cache.event.ApplicationDelayedEvent;
import com.newland.cache.key.CacheKeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 注解处理类
 *
 * @author liulun
 * @date 2023/2/28 11:11
 */
@Component
public class AnnotationInjectProcessor implements BeanDefinitionRegistryPostProcessor {

    protected final static Logger logger = LoggerFactory.getLogger(AnnotationInjectProcessor.class);

    private String keyGeneratorName = "";
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 需要处理的包名，只需要处理当前项目扫描路径和 @SpringBootApplication 配置的 scanBasePackages
        List<String> projectScanPackageNames = new ArrayList<>();
        // 入口类名、
        String mainClassName = null;
        // 参考代码： org.springframework.boot.SpringApplication.deduceMainApplicationClass
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            if ("main".equals(stackTraceElement.getMethodName())) {
                mainClassName = stackTraceElement.getClassName();
                break;
            }
        }
        Assert.notNull(mainClassName, "未找到入口类，程序异常！");
        // 当前项目包名
        String basePackageName = mainClassName.substring(0, mainClassName.lastIndexOf("."));
        projectScanPackageNames.add(basePackageName);
        try {
            Class<?> mainClass = Class.forName(mainClassName);
            SpringBootApplication springBootApplication = mainClass.getAnnotation(SpringBootApplication.class);
            String[] scanBasePackages = springBootApplication.scanBasePackages();
            if (null != scanBasePackages && scanBasePackages.length > 0) {
                projectScanPackageNames.addAll(Arrays.asList(scanBasePackages));
            }
        } catch (Exception e) {
            logger.info("get main class err, class = {}, reason = ", mainClassName, e);
        }
        String[] beanDefinitionNames = registry.getBeanDefinitionNames();
        // 找到自定义的key generator
        List<Class<?>> prepareToInjectList = new ArrayList<>(beanDefinitionNames.length);
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
            String beanClassName = beanDefinition.getBeanClassName();
            try {
                // 如果为空或者当前类就跳过
                if (!StringUtils.hasText(beanClassName) || this.getClass().getName().equals(beanClassName)) {
                    continue;
                }
                Class<?> cls = Class.forName(beanClassName);
                if (KeyGenerator.class.isAssignableFrom(cls)) {
                    keyGeneratorName = beanDefinitionName;
                }
                for (String scanPackageName : projectScanPackageNames) {
                    if (beanClassName.startsWith(scanPackageName)) {
                        prepareToInjectList.add(cls);
                        break;
                    }
                }
            } catch (Exception e) {
                logger.error("can not load class = {}, reason: {}", beanClassName, e);
            }
        }
        // 当前环境必须有一个key generator 否则报错
        Assert.isTrue(!StringUtils.hasText(keyGeneratorName),
                "current ioc environment must have one org.springframework.cache.interceptor.KeyGenerator instance!");
        // 开始修改注解的值
        for (Class<?> cls : prepareToInjectList) {
            injectAnnotationValue(cls, keyGeneratorName);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    /**
     * 修改当前类的 @Cacheable 、@CachePut 注解的属性值
     *
     * @param cls
     */
    protected void injectAnnotationValue(Class<?> cls, String keyGeneratorName) {
        CacheConfig cacheConfig = cls.getAnnotation(CacheConfig.class);
        if (null != cacheConfig && cacheConfig.cacheNames().length > 0 && StringUtils.hasText(cacheConfig.keyGenerator())) {
            CacheExpire cacheExpire = cls.getAnnotation(CacheExpire.class);
            if (null != cacheExpire) {
                applicationContext.publishEvent(new ApplicationDelayedEvent(cacheConfig.cacheNames()[0], cacheExpire.ttl()));
            }
            return; // 如果当前类通过@CacheConfig指定了cacheNames、kenGenerator就不处理
        }
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method method : declaredMethods) {
            InvocationHandler handler = null;
            try {
                Object annotation = method.getAnnotation(Cacheable.class);
                if (null == annotation) {
                    annotation = method.getAnnotation(CachePut.class);
                }
                if (null == annotation) {
                    annotation = method.getAnnotation(CacheEvict.class);
                }
                if (null == annotation) {
                    return;
                }
                handler = Proxy.getInvocationHandler(annotation);
                if (null != handler) {
                    injectAnnotationValue(cls, method, handler, keyGeneratorName);
                }
            } catch (Exception e) {
                logger.error("inject error : class=[{}] , method=[{}]  ! reason : {}", cls.getName(), method.getName(), e);
            }
        }
    }

    protected void injectAnnotationValue(Class<?> cls, Method method, InvocationHandler handler, String keyGeneratorName) throws Exception {
        // 获取 AnnotationInvocationHandler 的 memberValues 字段
        Field typeField = handler.getClass().getDeclaredField("type");
        ReflectionUtils.makeAccessible(typeField);
        String annotationTypeName = ((Class<? extends Annotation>) typeField.get(handler)).getName();
        Field memberValuesField = handler.getClass().getDeclaredField("memberValues");
        ReflectionUtils.makeAccessible(memberValuesField);
        Map<String, Object> memberValues = (Map<String, Object>) memberValuesField.get(handler);
        logger.debug("inject start class=[{}] , method=[{}], annotation=[{}] , memberValues={}", cls.getName(), method.getName(), annotationTypeName, JSON.toJSONString(memberValues));
        String[] cacheNames = (String[]) memberValues.get("cacheNames");
        String generateKey = CacheKeyGenerator.generate(cls.getName(), method.getName(), "");
        if (cacheNames.length == 0) {
            memberValues.put("cacheNames", new String[]{generateKey});
        }
        String keyGenerator = (String) memberValues.get("keyGenerator");
        // 指定的key和keyGenerator会冲突，所以判断一下不能影响框架之前的逻辑
        if (memberValues.get("key") == null && !StringUtils.hasText(keyGenerator)) {
            memberValues.put("keyGenerator", keyGeneratorName);
        }
        // 获得@CacheExpire注解解析失效时间
        CacheExpire cacheExpire = method.getAnnotation(CacheExpire.class);
        if (null == cacheExpire) {
            // 方法未添加就取当前类的注解
            cacheExpire = cls.getAnnotation(CacheExpire.class);
        }
        if (null != cacheExpire) {
            applicationContext.publishEvent(new ApplicationDelayedEvent(generateKey, cacheExpire.ttl()));
        }
    }

}
