package net.udp;

import java.io.IOException;
import java.net.*;

/**
 * 客户端
 * 1、创建客户端+端口
 * 2、准备数据
 * 3、打包（指定数据发送的地点以及端口）
 * 4、发送数据
 * 5、释放资源
 *
 * 思考 89.12 数据+类型
 * 只需将double数据转换成字节数组即可
 */
public class MyClient {
    public static void main(String[] args) throws IOException {
        /**
         * 1、创建服务端+端口
         * 在一台电脑上客户端和服务器的端口号不能相同
         */
        DatagramSocket ds = new DatagramSocket(6666);

        /**
         * 2、准备数据
         */
        String msg = "udp编程";
        byte[] data = msg.getBytes();
        /**
         * 3、打包（指定数据发送的地点以及端口）
         * DatagramPacket(byte[] buf, int length, InetAddress address, int port)
         *           构造数据报包，用来将长度为 length 的包发送到指定主机上的指定端口号。
         */
        DatagramPacket packet = new DatagramPacket(data, data.length,new InetSocketAddress("localhost",8888));

        /**
         * 发送数据
         */
        ds.send(packet);

        /**
         * 释放资源
         */
        ds.close();

    }
}
