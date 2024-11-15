package com.gaoyifeng.apigateway.session;

import com.gaoyifeng.apigateway.binding.MapperRegistry;
import com.gaoyifeng.apigateway.binding.IGenericReference;
import com.gaoyifeng.apigateway.mapping.HttpStatement;
import com.gaoyifeng.apigateway.rpc.IRpcSenderBuilder;
import com.gaoyifeng.apigateway.rpc.dubbo.DubboRpcSenderBuilder;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;

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

    private final MapperRegistry mapperRegistry = new MapperRegistry(this);

    private final Map<String, HttpStatement> httpStatements = new HashMap<>();

    public void addMapper(HttpStatement httpStatement) {
        mapperRegistry.addMapper(httpStatement);
    }

    public IGenericReference getMapper(String uri, GatewaySession gatewaySession) {
        return mapperRegistry.getMapper(uri, gatewaySession);
    }

    public void addHttpStatement(HttpStatement httpStatement) {
        httpStatements.put(httpStatement.getUri(), httpStatement);
    }

    public HttpStatement getHttpStatement(String uri) {
        return httpStatements.get(uri);
    }

    private IRpcSenderBuilder rpcSenderBuilder = new DubboRpcSenderBuilder();

    public IRpcSenderBuilder getRpcSenderBuilder() {
        return rpcSenderBuilder;
    }


    private final Map<String, ApplicationConfig> applicationConfigMap = new HashMap<>();
    private final Map<String, RegistryConfig> registryConfigMap = new HashMap<>();
    private final Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new HashMap<>();

    public Configuration() {
        // TODO 后期从配置中获取 初始化map
    }
    public ApplicationConfig getApplicationConfig(String applicationName) {
        return applicationConfigMap.get(applicationName);
    }

    public RegistryConfig getRegistryConfig(String applicationName) {
        return registryConfigMap.get(applicationName);
    }

    public ReferenceConfig<GenericService> getReferenceConfig(String interfaceName) {
        return referenceConfigMap.get(interfaceName);
    }



}