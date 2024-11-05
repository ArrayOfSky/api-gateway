package com.gaoyifeng.apigateway.generic.proxy;

import java.lang.reflect.Method;

import com.gaoyifeng.apigateway.generic.rpc.IRpcSender;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
 * @author gaoyifeng
 * @Classname GenericReferenceProxy
 * @Description TODO 代理IGenericReference 内聚 rpc包装
 * @Date 2024/11/3 22:00
 * @Created by gaoyifeng
 */
public class GenericReferenceProxy implements MethodInterceptor{

    /**
     * todo 泛化调用服务
     * RPC 泛化调用服务
     */
    private final IRpcSender rpcSender;

    /**
     * RPC 泛化调用方法
     */
    private final String methodName;

    public GenericReferenceProxy(IRpcSender rpcSender,String methodName) {
        this.rpcSender = rpcSender;
        this.methodName = methodName;
    }

    /**
     * 做一层代理控制，后续不止是可以使用 Dubbo 泛化调用，也可以是其他服务的泛化调用
     * 泛化调用文档：https://dubbo.apache.org/zh/docsv2.7/user/examples/generic-reference/
     */
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] parameters = new String[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parameters[i] = parameterTypes[i].getName();
        }
        //todo 泛化调用
        return rpcSender.invoke(methodName, parameters, args);
    }

}
