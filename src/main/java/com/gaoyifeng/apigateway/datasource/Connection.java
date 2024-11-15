package com.gaoyifeng.apigateway.datasource;

/**
 * @author gaoyifeng
 * @Classname Connection
 * @Description TODO
 * @Date 2024/11/15 18:58
 * @Created by gaoyifeng
 */
public interface Connection {

    Object execute(String method, String[] parameterTypes, String[] parameterNames, Object[] args);

}
