package com.gaoyifeng.apigateway.generic.rpc.dubbo;

import com.gaoyifeng.apigateway.generic.rpc.IRpcSender;
import org.apache.dubbo.rpc.service.GenericService;

/**
 * @author gaoyifeng
 * @Classname DubboRpcSender
 * @Description TODO
 * @Date 2024/11/4 8:03
 * @Created by gaoyifeng
 */
public class DubboRpcSender implements IRpcSender {

    /**
     * RPC 泛化调用服务
     */
    private final GenericService genericService;

    public DubboRpcSender(GenericService genericService) {
        this.genericService = genericService;
    }

    @Override
    public Object invoke(String methodName, String[] parameters, Object[] args) {
        return genericService.$invoke(methodName, parameters, args);
    }

}
