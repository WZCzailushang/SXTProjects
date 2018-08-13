package net.udp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 客户端
 * 1、创建客户端+端口
 * 2、准备数据  double  -->字节数组  字节数组输出流
 * 3、打包（指定数据发送的地点以及端口）
 * 4、发送数据
 * 5、释放资源
 * <p>
 * 思考 89.12 数据+类型
 * 只需将double数据转换成字节数组即可
 *
 * 虽然是用网络，但内部还是用流
 */
public class Client {
    public static void main(String[] args) throws IOException {
        /**
         * 1、创建客户端+端口
         * 在一台电脑上客户端和服务器的端口号不能相同
         */
        DatagramSocket ds = new DatagramSocket(6666);

        /**
         * 2、准备数据  可以包括数据+类型
         */
        double num = 89.12;
        byte[] data1 = convert(num);
        String msg = "udp编程";
        byte[] data = msg.getBytes();
        /**
         * 3、打包（指定数据发送的地点以及端口）
         * DatagramPacket(byte[] buf, int length, InetAddress address, int port)
         *           构造数据报包，用来将长度为 length 的包发送到指定主机上的指定端口号。
         */
        DatagramPacket packet = new DatagramPacket(data1, data1.length, new InetSocketAddress("localhost", 8888));

        /**
         * 发送数据
         */
        ds.send(packet);

        /**
         * 释放资源
         */
        ds.close();

    }

    /**
     * 字节数组  数据源+Data 输出流
     *
     * @param num
     * @return
     */
    public static byte[] convert(double num) throws IOException {
        byte[] data = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        DataOutputStream dos = new DataOutputStream(bos);

        dos.writeDouble(num);

        dos.flush();

        //获取数据

        data = bos.toByteArray();
        dos.close();

        return data;

    }
}
