package com.gaoyifeng.apigateway.session.defaults;

import com.gaoyifeng.apigateway.binding.IGenericReference;
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

    public DefaultGatewaySession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Object get(String uri, Object parameter) {
        return null;
    }

    @Override
    public IGenericReference getMapper(String uri) {
        return null;
    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }
}
