package net.udp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 服务端
 * 先创建服务端
 * 1、创建服务端+端口
 * 2、准备接收容器
 * 3、封装成包DatagramPacket
 * 4、接收数据  字节数组--->double  输入流
 * 5、分析数据
 */

public class Server {
    public static void main(String[] args) throws IOException {
        /**
         * 创建服务端+端口
         */
        DatagramSocket ds = new DatagramSocket(8888);
        /**
         * 准备接收容器
         */
        byte[] container = new byte[1024];
        /**
         * 封装成包
         * DatagramPacket(byte[] buf, int length)
         *           构造 DatagramPacket，用来接收长度为 length 的数据包。
         */
        DatagramPacket packet = new DatagramPacket(container, container.length);

        /**
         * 接收数据,将数据读到包中
         */
        ds.receive(packet);
        /**
         * 分析数据
         * 接收double数据类型并输出
         */
        byte[] data = packet.getData();
        double data1 = convert(data);
//        int len = packet.getLength();
//        System.out.println(new String(data, 0, len));
        System.out.println(data1);
        /**
         * 释放资源
         */
        ds.close();

    }

    /**
     * 字节数组 + Data 输入流
     * 将输入的字节数组转换成double类型
     *
     * @param data
     * @return
     */
    public static double convert(byte[] data) throws IOException {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
        double a = dis.readDouble();
        dis.close();
        return a;

    }


}
