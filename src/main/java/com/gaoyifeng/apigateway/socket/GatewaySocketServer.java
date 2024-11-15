package com.gaoyifeng.apigateway.socket;

import com.gaoyifeng.apigateway.session.Configuration;
import com.gaoyifeng.apigateway.session.defaults.DefaultGatewaySessionFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;


/**
 * @author gaoyifeng
 * @Classname SessionServer
 * @Description TODO
 * @Date 2024/11/3 18:58
 * @Created by gaoyifeng
 */
public class GatewaySocketServer implements Callable<Channel> {


    private final Logger logger = LoggerFactory.getLogger(GatewaySocketServer.class);
    private final Configuration configuration;
    private DefaultGatewaySessionFactory gatewaySessionFactory;

    public GatewaySocketServer(Configuration configuration, DefaultGatewaySessionFactory gatewaySessionFactory) {
        this.configuration = configuration;
        this.gatewaySessionFactory = gatewaySessionFactory;
    }

    private final EventLoopGroup boss = new NioEventLoopGroup(1);
    private final EventLoopGroup work = new NioEventLoopGroup();
    private Channel channel;

    @Override
    public Channel call() throws Exception {
        ChannelFuture channelFuture = null;
        try {
            // 创建ServerBootstrap实例
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 设置线程组
            serverBootstrap.group(boss, work)
                    // 设置通道类型为NioServerSocketChannel
                    .channel(NioServerSocketChannel.class)
                    // 设置socket参数 最大连接数 超过则返回错误
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 设置子处理器
                    .childHandler(new GatewayChannelInitializer(configuration, gatewaySessionFactory));

            // 绑定端口并异步启动服务器
            channelFuture = serverBootstrap.bind(new InetSocketAddress(8080)).syncUninterruptibly();
            this.channel = channelFuture.channel();
        } catch (Exception e) {
            // 记录启动错误日志
            logger.error("socket server start error.", e);
        } finally {
            // 检查绑定结果
            if (null != channelFuture && channelFuture.isSuccess()) {
                // 记录启动成功日志
                logger.info("socket server start done.");
            } else {
                // 记录启动失败日志
                logger.error("socket server start error.");
            }
        }

        return channel;
    }




}
