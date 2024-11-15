package com.gaoyifeng.apigateway.datasource.connection;

import com.gaoyifeng.apigateway.datasource.Connection;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author gaoyifeng
 * @Classname DubboConnection
 * @Description TODO
 * @Date 2024/11/15 19:27
 * @Created by gaoyifeng
 */
public class DubboConnection  implements Connection {

    private final GenericService genericService;

    public DubboConnection(ApplicationConfig applicationConfig, RegistryConfig registryConfig, ReferenceConfig<GenericService> reference) {
        // 连接远程服务
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(applicationConfig).registry(registryConfig).reference(reference).start();
        // 获取泛化接口
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        genericService = cache.get(reference);
    }


    @Override
    public Object execute(String method, String[] parameterTypes, String[] parameterNames, Object[] args) {
        return genericService.$invoke(method, parameterTypes, args);
    }


}
