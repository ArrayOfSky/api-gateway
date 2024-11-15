package com.gaoyifeng.apigateway.executor;

import com.gaoyifeng.apigateway.datasource.Connection;
import com.gaoyifeng.apigateway.session.Configuration;

/**
 * @author gaoyifeng
 * @Classname SimpleExecutor
 * @Description TODO
 * @Date 2024/11/15 20:30
 * @Created by gaoyifeng
 */
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Connection connection) {
        super(configuration, connection);
    }

    @Override
    protected Object doExec(String methodName, String[] parameterTypes, Object[] args) {
        return connection.execute(methodName, parameterTypes, new String[]{"ignore"}, args);
    }

}
