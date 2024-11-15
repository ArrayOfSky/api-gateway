package com.gaoyifeng.apigateway.session;

/**
 * @author gaoyifeng
 * @Classname GatewaySessionFactory
 * @Description TODO
 * @Date 2024/11/7 21:17
 * @Created by gaoyifeng
 */
public interface GatewaySessionFactory {


    GatewaySession openSession(String uri);

}
