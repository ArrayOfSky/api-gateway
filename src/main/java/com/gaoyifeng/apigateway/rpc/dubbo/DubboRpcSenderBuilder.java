package com.gaoyifeng.apigateway.rpc.dubbo;

import com.gaoyifeng.apigateway.rpc.IRpcSender;
import com.gaoyifeng.apigateway.rpc.IRpcSenderBuilder;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.config.utils.ReferenceConfigCache;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author gaoyifeng
 * @Classname DubboRpcSenderBuilder
 * @Description TODO
 * @Date 2024/11/4 8:32
 * @Created by gaoyifeng
 */
public class DubboRpcSenderBuilder implements IRpcSenderBuilder {

    @Override
    public IRpcSender build(String application, String interfaceName) {
        // TODO 后期从配置中获取
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(application);
        applicationConfig.setQosEnable(false);

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        registryConfig.setRegister(false);

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        reference.setInterface(interfaceName);
        reference.setVersion("1.0.0");
        reference.setGeneric("true");

        // 构建Dubbo服务
        DubboBootstrap bootstrap = DubboBootstrap.getInstance();
        bootstrap.application(applicationConfig).registry(registryConfig).reference(reference).start();
        // 获取泛化调用服务
        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(reference);
        return new DubboRpcSender(genericService);
    }
}
