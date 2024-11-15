package com.gaoyifeng.apigateway.session;

import com.gaoyifeng.apigateway.binding.IGenericReference;

import java.util.Map;

/**
 * @author gaoyifeng
 * @Classname GatewaySession
 * @Description TODO
 * @Date 2024/11/7 21:01
 * @Created by gaoyifeng
 */
public interface GatewaySession {

    Object get(String methodName, Map<String, Object> params);

    Object post(String methodName, Map<String, Object> params);

    IGenericReference getMapper(String uri);

    Configuration getConfiguration();

}