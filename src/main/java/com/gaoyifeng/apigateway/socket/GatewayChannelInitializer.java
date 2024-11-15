package com.gaoyifeng.apigateway.socket;

import com.gaoyifeng.apigateway.session.Configuration;
import com.gaoyifeng.apigateway.session.defaults.DefaultGatewaySessionFactory;
import com.gaoyifeng.apigateway.socket.handlers.AuthorizationHandler;
import com.gaoyifeng.apigateway.socket.handlers.GatewayServerHandler;
import com.gaoyifeng.apigateway.socket.handlers.ProtocolDataHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * @author gaoyifeng
 * @Classname SessionChannelInitializer
 * @Description TODO是一个特殊的handler inboundhandler
 * @Date 2024/11/3 19:22
 * @Created by gaoyifeng
 */
public class GatewayChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final Configuration configuration;
    private final DefaultGatewaySessionFactory gatewaySessionFactory;

    public GatewayChannelInitializer(Configuration configuration, DefaultGatewaySessionFactory gatewaySessionFactory) {
        this.configuration = configuration;
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline line = channel.pipeline();
        line.addLast(new HttpRequestDecoder());
        line.addLast(new HttpResponseEncoder());
        line.addLast(new HttpObjectAggregator(1024 * 1024));
        line.addLast(new GatewayServerHandler(configuration));
        line.addLast(new AuthorizationHandler(configuration));
        line.addLast(new ProtocolDataHandler(gatewaySessionFactory));
    }

}
