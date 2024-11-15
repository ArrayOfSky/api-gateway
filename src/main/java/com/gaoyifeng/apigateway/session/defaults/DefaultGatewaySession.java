package com.gaoyifeng.apigateway.session.defaults;

import com.gaoyifeng.apigateway.binding.IGenericReference;
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

    public DefaultGatewaySession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Object get(String uri, Object parameter) {
        HttpStatement httpStatement = configuration.getHttpStatement(uri);
        IRpcSenderBuilder rpcSenderBuilder = configuration.getRpcSenderBuilder();
        IRpcSender sender = rpcSenderBuilder.build(httpStatement.getApplication(), httpStatement.getInterfaceName());
        Object result = sender.invoke(httpStatement.getMethodName(), new String[]{"java.lang.String"}, new Object[]{"小傅哥"});
        return result;
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
