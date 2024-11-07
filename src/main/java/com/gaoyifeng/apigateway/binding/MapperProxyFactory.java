package com.gaoyifeng.apigateway.binding;

import com.gaoyifeng.apigateway.generic.rpc.IRpcSender;
import net.sf.cglib.core.Signature;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InterfaceMaker;
import org.objectweb.asm.Type;

/**
 * @author gaoyifeng
 * @Classname GenericReferenceProxyFactory
 * @Description TODO
 * @Date 2024/11/3 22:06
 * @Created by gaoyifeng
 */
public class MapperProxyFactory {


    /**
     * todo 泛化调用服务
     * RPC 泛化调用服务
     */
    private final IRpcSender rpcSender;


    public MapperProxyFactory(IRpcSender rpcSender) {
        this.rpcSender = rpcSender;
    }


    public IGenericReference newInstance(String method) {
        // 泛化调用
        MapperProxy genericReferenceProxy = new MapperProxy(rpcSender,method);
        // 创建接口
        InterfaceMaker interfaceMaker = new InterfaceMaker();
        interfaceMaker.add(new Signature(method, Type.getType(String.class), new Type[]{Type.getType(String.class)}), null);
        Class<?> interfaceClass = interfaceMaker.create();
        // 代理对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Object.class);
        // IGenericReference 统一泛化调用接口
        // interfaceClass    根据泛化调用注册信息创建的接口，建立 http -> rpc 关联
        enhancer.setInterfaces(new Class[]{IGenericReference.class, interfaceClass});
        enhancer.setCallback(genericReferenceProxy);
        return (IGenericReference) enhancer.create();
    }

}
