package com.gaoyifeng.apigateway.binding;

import com.gaoyifeng.apigateway.mapping.HttpCommandType;
import com.gaoyifeng.apigateway.session.Configuration;
import com.gaoyifeng.apigateway.session.GatewaySession;

import java.lang.reflect.Method;

/**
 * @author gaoyifeng
 * @Classname MapperMethod
 * @Description TODO 绑定调用方法
 * @Date 2024/11/7 21:00
 * @Created by gaoyifeng
 */
public class MapperMethod {

    private String methodName;
    private final HttpCommandType command;

    public MapperMethod(String uri, Method method, Configuration configuration) {
        this.methodName = configuration.getHttpStatement(uri).getMethodName();
        this.command = configuration.getHttpStatement(uri).getHttpCommandType();
    }

    public Object execute(GatewaySession session, Object args) {
        Object result = null;
        switch (command) {
            // 暂且实现GET方法代理
            case GET:
                result = session.get(methodName, args);
                break;
            case POST:
                break;
            case PUT:
                break;
            case DELETE:
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command);
        }
        return result;
    }


}
