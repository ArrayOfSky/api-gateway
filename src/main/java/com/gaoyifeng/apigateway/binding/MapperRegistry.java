package com.gaoyifeng.apigateway.binding;

import com.gaoyifeng.apigateway.session.Configuration;
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
public class MapperRegistry {


    private final Configuration configuration;

    public MapperRegistry(Configuration configuration) {
        this.configuration = configuration;
    }

    // 泛化调用静态代理工厂
    private final Map<String, MapperProxyFactory> genericReferenceProxyFactoryCache = new HashMap<>();

    public IGenericReference getGenericReference(String methodName) {
        MapperProxyFactory genericReferenceProxyFactory = genericReferenceProxyFactoryCache.get(methodName);
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
        genericReferenceProxyFactoryCache.put(methodName, new MapperProxyFactory(rpcSender));
    }

}
