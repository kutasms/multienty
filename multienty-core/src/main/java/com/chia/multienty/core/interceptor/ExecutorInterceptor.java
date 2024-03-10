package com.chia.multienty.core.interceptor;


import com.chia.multienty.core.domain.enums.HttpResultEnum;
import com.chia.multienty.core.exception.KutaRuntimeException;
import com.chia.multienty.core.tools.MultientyContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Component;

import java.sql.Statement;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = { Statement.class })})
@Slf4j
@Component
public class ExecutorInterceptor implements Interceptor {

        @Override
        public Object intercept(Invocation invocation) throws Throwable {
            Object target = invocation.getTarget();
            StatementHandler statementHandler = (StatementHandler)target;

            BoundSql boundSql = statementHandler.getBoundSql();

            String trimSql = boundSql.getSql().trim().toLowerCase();
            if(MultientyContext.isTestAcc()) {
                if(trimSql.startsWith("update")
                        || trimSql.startsWith("insert")
                        || trimSql.startsWith("delete")
                        || trimSql.startsWith("save")
                        || trimSql.startsWith("add")
                        || trimSql.startsWith("remove")
                        || trimSql.startsWith("batch")
                        || trimSql.startsWith("edit")
                        || trimSql.startsWith("change")
                        || trimSql.startsWith("modify")) {
                    throw new KutaRuntimeException(HttpResultEnum.ACCOUNT_NOT_PERMITTED);
                }
            }

            return invocation.proceed();
        }

        @Override
        public Object plugin(Object o) {
            return Plugin.wrap(o,
                    this);
        }

        @Override
        public void setProperties(Properties properties) {

        }
}
