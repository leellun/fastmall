package com.newland.mybatis.interceptor;

import com.newland.mall.model.BaseEntity;
import com.newland.mybatis.sequence.Sequence;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Properties;

/**
 * 时间处理
 * Author: leell
 * Date: 2023/2/22 15:47:12
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
@Component
public class HandleTimeInterceptor implements Interceptor {
    private Sequence sequence = new Sequence();

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        if (sqlCommandType == SqlCommandType.UPDATE) {
            if (args[1] instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) args[1];
                if (baseEntity.getUpdateTime() == null) {
                    baseEntity.setUpdateTime(LocalDateTime.now());
                }
            }
        } else if (sqlCommandType == SqlCommandType.INSERT) {
            if (args[1] instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) args[1];
                baseEntity.setDeleted(0);
                baseEntity.setId(sequence.nextId());
                if (baseEntity.getCreateTime() == null) {
                    baseEntity.setCreateTime(LocalDateTime.now());
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
