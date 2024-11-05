# 简介

api-gateway 为本人基于netty自研的api网关，目前处于v1版本开发中。。

# api-gateway-v1

## 版本规划

- [x] 实现解析http请求
- [x] 实现包装rpc协议
- [x] 实现对接注册中心（v1版本为redis）

## 文档

暂时放在readme：

### 前置知识

#### netty的使用

**简介**

ServerBootstrap是Netty用于启动服务器的工具类，它提供了一系列的流式方法来配置服务器的网络层选项、线程模型和业务处理逻辑。

**组件**

- `Channel` 连接的对象
    - Channel是Netty中表示连接的组件，可以理解为通道或连接。
    - Netty中的每个连接都会对应一个Channel对象，该对象中包含了连接的所有相关信息，如当前的连接状态、配置参数、以及绑定的事件处理器等。
- `EventLoopGroup` 处理线程池
    - EventLoopGroup是一组EventLoop的抽象，它用于处理Channel上的事件，如连接接受、数据读写等。
    - Netty支持多种EventLoopGroup实现，以适应不同的网络模型。
- `ChannelInitializer` 配置执行处理链
    - ChannelInitializer是一个特殊的ChannelInboundHandler，用于在某个Channel注册到EventLoop后，对这个Channel执行初始化操作。
    - 这通常是配置ChannelPipeline的地方，ChannelPipeline用于存放处理网络事件的Handler链。

**常用配置方法**
- group
    - `ServerBootstrap group(EventLoopGroup parentGroup, EventLoopGroup childGroup)`
    - 设置用于**接受客户端连接的parentGroup，以及处理客户端数据读写的childGroup。**
- channel
    - `ServerBootstrap channel(Class<? extends ServerChannel> channelClass)`
    - 指定服务器端Channel的实现。Netty支持多种类型的传输协议。对于NIO传输，通常设置为NioServerSocketChannel.class。
- option和childOption
    - 区别： option对应boss child对应worker
    - `ServerBootstrap option(ChannelOption<T> option, T value)`
    - 用途：设置ServerChannel的参数。
        - SO_BACKLOG 设置服务器端接受连接的队列长度。如果队列已满，客户端连接将被拒绝。
        - SO_KEEPALIVE 启用或禁用TCP心跳机制，保持连接活跃，自动检测断开的连接。
        - TCP_NODELAY 启用或禁用TCP的Nagle算法。如果启用，将会减少网络延迟。常用于游戏服务器开发
        - SO_REUSEADDR 允许启动一个监听服务器并捆绑其端口，即使之前的连接仍在TIME_WAIT状态。快速重启服务器时避免地址被占用错误。
        - SO_LINGER 设置关闭socket的等待时间，用于控制socket关闭时的行为。确保数据完全发送完毕后再关闭连接。
        - SO_TIMEOUT 设置读操作的超时时间。防止读操作无限期地阻塞。
        - SO_SNDBUF和SO_RCVBUF 分别设置输出和输入数据的缓冲区大小。控制数据包的传输速度。
        - CONNECT_TIMEOUT_MILLIS 设置连接超时时间，以毫秒为单位。
        - WRITE_BUFFER_WATER_MARK 设置写缓冲区的高低水位线。当写缓冲区的数据量超过高水位线时，Channel的isWritable将变为false，低于低水位线时变为true。控制写入速度，防止发送方过快地发送数据造成接收方处理不过来
- handler
    - `ServerBootstrap handler(ChannelHandler handler)`
    - 设置parentGroup的处理程序。
- childHandler
    - `ServerBootstrap childHandler(ChannelHandler childHandler)`
    - 设置childGroup的处理程序，即处理客户端网络I/O事件的处理器。这是开发者编写业务逻辑处理代码的地方，通常通过添加一系列的ChannelHandler来实现。

