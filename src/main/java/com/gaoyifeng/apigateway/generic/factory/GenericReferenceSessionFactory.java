package com.gaoyifeng.apigateway.generic.factory;

import com.gaoyifeng.apigateway.session.Configuration;
import com.gaoyifeng.apigateway.socket.GatewaySocketServer;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author gaoyifeng
 * @Classname GenericReferenceSessionFactory
 * @Description TODO
 * @Date 2024/11/3 21:48
 * @Created by gaoyifeng
 */
public class GenericReferenceSessionFactory implements IGenericReferenceSessionFactory{


    private final Logger logger = LoggerFactory.getLogger(GenericReferenceSessionFactory.class);

    private final Configuration configuration;

    public GenericReferenceSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Future<Channel> openSession() throws ExecutionException, InterruptedException {
        GatewaySocketServer server = new GatewaySocketServer(configuration);

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(server);
        Channel channel = future.get();

        if (null == channel) {
            throw new RuntimeException("netty server start error channel is null");
        }

        while (!channel.isActive()) {
            logger.info("netty server gateway start Ing ...");
            Thread.sleep(500);
        }
        logger.info("netty server gateway start Done! {}", channel.localAddress());

        return future;
    }

}
