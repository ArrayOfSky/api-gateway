package com.gaoyifeng.apigateway.session;

import com.gaoyifeng.apigateway.binding.MapperRegistry;
import com.gaoyifeng.apigateway.binding.IGenericReference;

/**
 * @author gaoyifeng
 * @Classname Configuration
 * @Description TODO 会话生命周期配置项
 * @Date 2024/11/3 21:44
 * @Created by gaoyifeng
 */
public class Configuration {

    private final MapperRegistry registry = new MapperRegistry(this);

    public void addGenericReference(String application, String interfaceName, String methodName) {
        registry.addGenericReference(application, interfaceName, methodName);
    }

    public IGenericReference getGenericReference(String methodName) {
        return registry.getGenericReference(methodName);
    }

}