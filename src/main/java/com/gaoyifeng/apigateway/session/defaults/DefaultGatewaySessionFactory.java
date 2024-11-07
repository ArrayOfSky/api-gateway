package com.gaoyifeng.apigateway.session.defaults;

import com.gaoyifeng.apigateway.session.Configuration;
import com.gaoyifeng.apigateway.session.GatewaySession;
import com.gaoyifeng.apigateway.session.GatewaySessionFactory;

/**
 * @author gaoyifeng
 * @Classname DefaultGatewaySessionFactory
 * @Description TODO
 * @Date 2024/11/7 21:18
 * @Created by gaoyifeng
 */
public class DefaultGatewaySessionFactory implements GatewaySessionFactory {

    private final Configuration configuration;

    public DefaultGatewaySessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }


    @Override
    public GatewaySession openSession() {
        return new DefaultGatewaySession(configuration);
    }



}
