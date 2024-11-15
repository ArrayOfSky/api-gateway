package com.gaoyifeng.apigateway.binding;

import java.lang.reflect.Method;

import com.gaoyifeng.apigateway.session.GatewaySession;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
 * @author gaoyifeng
 * @Classname GenericReferenceProxy
 * @Description TODO 代理IGenericReference 内聚 rpc包装
 * @Date 2024/11/3 22:00
 * @Created by gaoyifeng
 */
public class MapperProxy implements MethodInterceptor{

    private GatewaySession gatewaySession;
    private final String uri;

    public MapperProxy(GatewaySession gatewaySession, String uri) {
        this.gatewaySession = gatewaySession;
        this.uri = uri;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        MapperMethod linkMethod = new MapperMethod(uri, method, gatewaySession.getConfiguration());
        return linkMethod.execute(gatewaySession, args);
    }

}
