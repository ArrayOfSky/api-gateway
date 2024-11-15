package com.gaoyifeng.apigateway.session;

import com.gaoyifeng.apigateway.authorization.AuthService;
import com.gaoyifeng.apigateway.binding.MapperRegistry;
import com.gaoyifeng.apigateway.binding.IGenericReference;
import com.gaoyifeng.apigateway.datasource.Connection;
import com.gaoyifeng.apigateway.executor.Executor;
import com.gaoyifeng.apigateway.executor.SimpleExecutor;
import com.gaoyifeng.apigateway.mapping.HttpStatement;
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

    public void addMapper(HttpStatement httpStatement) {
        mapperRegistry.addMapper(httpStatement);
    }

    public IGenericReference getMapper(String uri, GatewaySession gatewaySession) {
        return mapperRegistry.getMapper(uri, gatewaySession);
    }


    private final Map<String, HttpStatement> httpStatements = new HashMap<>();


    public void addHttpStatement(HttpStatement httpStatement) {
        httpStatements.put(httpStatement.getUri(), httpStatement);
    }

    public HttpStatement getHttpStatement(String uri) {
        return httpStatements.get(uri);
    }

    private final Map<String, ApplicationConfig> applicationConfigMap = new HashMap<>();
    private final Map<String, RegistryConfig> registryConfigMap = new HashMap<>();
    private final Map<String, ReferenceConfig<GenericService>> referenceConfigMap = new HashMap<>();

    public synchronized void registryConfig(String applicationName, String address, String interfaceName, String version) {
        if (applicationConfigMap.get(applicationName) == null) {
            ApplicationConfig application = new ApplicationConfig();
            application.setName(applicationName);
            application.setQosEnable(false);
            applicationConfigMap.put(applicationName, application);
        }

        if (registryConfigMap.get(applicationName) == null) {
            RegistryConfig registry = new RegistryConfig();
            registry.setAddress(address);
            registry.setRegister(false);
            registryConfigMap.put(applicationName, registry);
        }

        if (referenceConfigMap.get(interfaceName) == null) {
            ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
            reference.setInterface(interfaceName);
            reference.setVersion(version);
            reference.setGeneric("true");
            referenceConfigMap.put(interfaceName, reference);
        }
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

    public Executor newExecutor(Connection connection) {
        return new SimpleExecutor(this, connection);
    }


    private AuthService auth = new AuthService();

    public boolean authValidate(String uId, String token) {
        return auth.validate(uId, token);
    }


}