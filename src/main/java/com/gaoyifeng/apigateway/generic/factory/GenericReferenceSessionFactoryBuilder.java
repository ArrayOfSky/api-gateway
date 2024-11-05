package com.gaoyifeng.apigateway.generic.factory;

import com.gaoyifeng.apigateway.generic.Configuration;
import io.netty.channel.Channel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author gaoyifeng
 * @Classname GenericReferenceSessionFactoryBuilder
 * @Description TODO
 * @Date 2024/11/3 21:51
 * @Created by gaoyifeng
 */
public class GenericReferenceSessionFactoryBuilder {

    public Future<Channel> build(Configuration configuration) {
        IGenericReferenceSessionFactory genericReferenceSessionFactory = new GenericReferenceSessionFactory(configuration);
        try {
            return genericReferenceSessionFactory.openSession();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
