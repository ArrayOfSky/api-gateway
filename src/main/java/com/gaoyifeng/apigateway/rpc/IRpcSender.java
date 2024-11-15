package com.gaoyifeng.apigateway.rpc;

/**
 * @author gaoyifeng
 * @Classname IRpcSender
 * @Description TODO
 * @Date 2024/11/4 8:00
 * @Created by gaoyifeng
 */
public interface IRpcSender {


    Object invoke(String methodName, String[] parameters, Object[] args);


}
