package com.gaoyifeng.apigateway.generic.proxy;

import com.gaoyifeng.apigateway.generic.Configuration;
import com.gaoyifeng.apigateway.generic.rpc.IRpcSender;
import com.gaoyifeng.apigateway.generic.rpc.IRpcSenderBuilder;
import com.gaoyifeng.apigateway.generic.rpc.dubbo.DubboRpcSenderBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaoyifeng
 * @Classname GenericReferenceRegistry
 * @Description TODO
 * @Date 2024/11/3 22:08
 * @Created by gaoyifeng
 */
public class GenericReferenceRegistry {


    private final Configuration configuration;

    public GenericReferenceRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    // 泛化调用静态代理工厂
    private final Map<String, GenericReferenceProxyFactory> genericReferenceProxyFactoryCache = new HashMap<>();

    public IGenericReference getGenericReference(String methodName) {
        GenericReferenceProxyFactory genericReferenceProxyFactory = genericReferenceProxyFactoryCache.get(methodName);
        if (genericReferenceProxyFactory == null) {
            throw new RuntimeException("Type " + methodName + " is not known to the GenericReferenceRegistry.");
        }
        return genericReferenceProxyFactory.newInstance(methodName);
    }

    /**
     * 注册泛化调用服务接口方法
     */
    public void addGenericReference(String application, String interfaceName, String methodName) {
        //todo 注册泛化服务
        IRpcSenderBuilder rpcSenderBuilder = new DubboRpcSenderBuilder();
        IRpcSender rpcSender = rpcSenderBuilder.build(application, interfaceName);
        // 创建并保存泛化工厂
        genericReferenceProxyFactoryCache.put(methodName, new GenericReferenceProxyFactory(rpcSender));
    }

}
