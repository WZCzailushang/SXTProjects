package net.tcp.Demo03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 客户端：向服务器发送数据
 * 也可以从服务器接收数据
 * 通过多线程的方式，不断地同时发送和读取
 * 输入流与输出流独立，在输出的同时可以输入
 *
 * 加入了名称  可以显示是哪个人加入了聊天室
 * 增加了私聊功能，通过@(用户名):   可以达到单独和此用户通信的目的
 *
 */
public class MultiClient {
    private static Socket client;


    public static void main(String[] args) throws IOException {
        System.out.println("请输入用户名：");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();

        if (null == name || name.equals("")) {
            return;
        }
        try {
            /**
             * 这里也可以用IP地址来代替
             */
            client = new Socket("192.168.3.16", 8888);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        new Thread(new Send(client, name)).start();
        new Thread(new Receive(client)).start();
    }

}

