package com.gaoyifeng.apigateway.generic.factory;

import io.netty.channel.Channel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author gaoyifeng
 * @Classname IGenericReferenceSessionFactory
 * @Description TODO 泛化调用工厂 对 SessionServer的进一步封装
 * @Date 2024/11/3 21:43
 * @Created by gaoyifeng
 */
public interface IGenericReferenceSessionFactory {

    Future<Channel> openSession() throws ExecutionException, InterruptedException;

}
