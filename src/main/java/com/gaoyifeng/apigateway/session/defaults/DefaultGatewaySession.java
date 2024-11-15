package com.gaoyifeng.apigateway.session.defaults;

import com.gaoyifeng.apigateway.binding.IGenericReference;
import com.gaoyifeng.apigateway.datasource.Connection;
import com.gaoyifeng.apigateway.datasource.DataSource;
import com.gaoyifeng.apigateway.mapping.HttpStatement;
import com.gaoyifeng.apigateway.rpc.IRpcSender;
import com.gaoyifeng.apigateway.rpc.IRpcSenderBuilder;
import com.gaoyifeng.apigateway.session.Configuration;
import com.gaoyifeng.apigateway.session.GatewaySession;

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
    private DataSource dataSource;

    public DefaultGatewaySession(Configuration configuration, String uri, DataSource dataSource) {
        this.configuration = configuration;
        this.uri = uri;
        this.dataSource = dataSource;
    }

    @Override
    public Object get(String methodName, Object parameter) {
        Connection connection = dataSource.getConnection();
        return connection.execute(methodName, new String[]{"java.lang.String"}, new String[]{"name"}, new Object[]{parameter});
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
