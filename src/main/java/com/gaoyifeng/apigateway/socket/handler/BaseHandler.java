package com.gaoyifeng.apigateway.socket.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author gaoyifeng
 * @Classname BaseHandler
 * @Description TODO
 * @Date 2024/11/3 19:19
 * @Created by gaoyifeng
 */
public abstract class BaseHandler<T> extends SimpleChannelInboundHandler <T> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, T msg) throws Exception {
        session(ctx, ctx.channel(), msg);
    }

    protected abstract void session(ChannelHandlerContext ctx, final Channel channel, T request);

}
