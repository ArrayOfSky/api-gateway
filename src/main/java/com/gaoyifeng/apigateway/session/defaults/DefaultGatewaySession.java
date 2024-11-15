package com.gaoyifeng.apigateway.session.defaults;

import com.gaoyifeng.apigateway.binding.IGenericReference;
import com.gaoyifeng.apigateway.datasource.Connection;
import com.gaoyifeng.apigateway.datasource.DataSource;
import com.gaoyifeng.apigateway.executor.Executor;
import com.gaoyifeng.apigateway.mapping.HttpStatement;
import com.gaoyifeng.apigateway.rpc.IRpcSender;
import com.gaoyifeng.apigateway.rpc.IRpcSenderBuilder;
import com.gaoyifeng.apigateway.session.Configuration;
import com.gaoyifeng.apigateway.session.GatewaySession;
import com.gaoyifeng.apigateway.type.SimpleTypeRegistry;

import java.util.Map;

/**
 * @author gaoyifeng
 * @Classname DefaultGatewaySession
 * @Description TODO
 * @Date 2024/11/7 21:18
 * @Created by gaoyifeng
 */
public class DefaultGatewaySession implements GatewaySession{

    private Configuration configuration;
    private String uri;
    private   Executor executor;

    public DefaultGatewaySession(Configuration configuration, String uri,  Executor executor) {
        this.configuration = configuration;
        this.uri = uri;
        this.executor = executor;
    }

    @Override
    public Object get(String methodName,Map<String, Object> params) {
        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        try {
            return executor.exec(httpStatement, params);
        } catch (Exception e) {
            throw new RuntimeException("Error exec get. Cause: " + e);
        }
    }

    @Override
    public Object post(String methodName, Map<String, Object> params) {
        return get(methodName, params);
    }

    @Override
    public IGenericReference getMapper(String uri) {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }

}
