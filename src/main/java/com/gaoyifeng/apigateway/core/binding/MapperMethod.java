package com.gaoyifeng.apigateway.core.binding;

import com.gaoyifeng.apigateway.core.mapping.HttpCommandType;
import com.gaoyifeng.apigateway.core.session.Configuration;
import com.gaoyifeng.apigateway.core.session.GatewaySession;

import java.lang.reflect.Method;
import java.util.Map;

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

    public Object execute(GatewaySession session, Map<String, Object> params) {
        Object result = null;
        switch (command) {
            case GET:
                result = session.get(methodName, params);
                break;
            case POST:
                result = session.post(methodName, params);
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
