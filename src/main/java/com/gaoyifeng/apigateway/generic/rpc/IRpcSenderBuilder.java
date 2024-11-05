package com.gaoyifeng.apigateway.generic.rpc;

/**
 * @author gaoyifeng
 * @Classname IRpcSenderBuilder
 * @Description TODO
 * @Date 2024/11/4 8:40
 * @Created by gaoyifeng
 */
public interface IRpcSenderBuilder {

    IRpcSender build(String application,String interfaceName);


}
