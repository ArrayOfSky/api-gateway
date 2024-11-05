package com.gaoyifeng.apigateway.generic;

import com.gaoyifeng.apigateway.generic.proxy.GenericReferenceRegistry;
import com.gaoyifeng.apigateway.generic.proxy.IGenericReference;

/**
 * @author gaoyifeng
 * @Classname Configuration
 * @Description TODO 会话生命周期配置项
 * @Date 2024/11/3 21:44
 * @Created by gaoyifeng
 */
public class Configuration {

    private final GenericReferenceRegistry registry = new GenericReferenceRegistry(this);

    public void addGenericReference(String application, String interfaceName, String methodName) {
        registry.addGenericReference(application, interfaceName, methodName);
    }

    public IGenericReference getGenericReference(String methodName) {
        return registry.getGenericReference(methodName);
    }

}