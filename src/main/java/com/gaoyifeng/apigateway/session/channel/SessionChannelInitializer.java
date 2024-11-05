package com.gaoyifeng.apigateway.session.channel;

import com.gaoyifeng.apigateway.generic.Configuration;
import com.gaoyifeng.apigateway.session.handler.SessionServerHandler;
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
public class SessionChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final Configuration configuration;

    public SessionChannelInitializer(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline line = channel.pipeline();
        line.addLast(new HttpRequestDecoder());
        line.addLast(new HttpResponseEncoder());
        line.addLast(new HttpObjectAggregator(1024 * 1024));
        line.addLast(new SessionServerHandler(configuration));
    }

}
