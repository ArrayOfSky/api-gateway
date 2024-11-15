package com.gaoyifeng.apigateway.socket.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.gaoyifeng.apigateway.session.Configuration;
import com.gaoyifeng.apigateway.binding.IGenericReference;
import com.gaoyifeng.apigateway.session.GatewaySession;
import com.gaoyifeng.apigateway.session.defaults.DefaultGatewaySessionFactory;
import com.gaoyifeng.apigateway.socket.agreement.RequestParser;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @author gaoyifeng
 * @Classname SessionServerHandler
 * @Description TODO
 * @Date 2024/11/3 19:21
 * @Created by gaoyifeng
 */
public class GatewayServerHandler extends BaseHandler<FullHttpRequest>{


    private final Logger logger = LoggerFactory.getLogger(GatewayServerHandler.class);

    private final DefaultGatewaySessionFactory gatewaySessionFactory;

    public GatewayServerHandler(DefaultGatewaySessionFactory gatewaySessionFactory) {
        this.gatewaySessionFactory = gatewaySessionFactory;
    }
    @Override
    protected void session(ChannelHandlerContext ctx, final Channel channel, FullHttpRequest request) {
        logger.info("网关接收请求 uri：{} method：{}", request.uri(), request.method());

        //解析request
        Map<String, Object> requestObj = new RequestParser(request).parse();

        // 返回信息控制：简单处理
        String uri = request.uri();
        int idx = uri.indexOf("?");
        uri = idx > 0 ? uri.substring(0, idx) : uri;
        if (uri.equals("/favicon.ico")) return;


        // 服务泛化调用
        GatewaySession gatewaySession = gatewaySessionFactory.openSession(uri);
        IGenericReference reference = gatewaySession.getMapper(uri);
        String result = reference.invoke(requestObj) + " " + System.currentTimeMillis();


        // response处理
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.content().writeBytes(JSON.toJSONBytes(result, SerializerFeature.PrettyFormat));
        HttpHeaders heads = response.headers();
        heads.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON + "; charset=UTF-8");
        heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        channel.writeAndFlush(response);
    }

}
