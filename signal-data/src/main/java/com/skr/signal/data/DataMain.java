package com.skr.signal.data;

import com.skr.signal.data.initialize.WebSocketChanelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author mqw
 * @create 2020-05-28-16:44
 */
public class DataMain {

    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup(5);
        EventLoopGroup worker = new NioEventLoopGroup(3);
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new WebSocketChanelInitializer());
            bootstrap.bind(9001).sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}