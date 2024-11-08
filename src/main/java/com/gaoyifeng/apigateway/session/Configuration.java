package com.gaoyifeng.apigateway.session;

import com.gaoyifeng.apigateway.binding.MapperRegistry;
import com.gaoyifeng.apigateway.binding.IGenericReference;
import com.gaoyifeng.apigateway.mapping.HttpStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoyifeng
 * @Classname Configuration
 * @Description TODO 会话生命周期配置项
 * @Date 2024/11/3 21:44
 * @Created by gaoyifeng
 */
public class Configuration {

    // Mapper注册机
    private final MapperRegistry mapperRegistry = new MapperRegistry(this);

    // url-httpstatement请求映射
    private final Map<String, HttpStatement> httpStatements = new HashMap<>();


    public void addHttpStatement(HttpStatement httpStatement) {
        httpStatements.put(httpStatement.getUri(), httpStatement);
    }

    public HttpStatement getHttpStatement(String uri) {
        return httpStatements.get(uri);
    }


    public IGenericReference getGenericReference(String methodName) {
        return mapperRegistry.getGenericReference(methodName);
    }

}