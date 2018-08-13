package net.tcp.Demo01;

import java.io.IOException;
import java.net.Socket;

/**
 * 客户端：向服务器发送数据
 * 也可以从服务器接收数据
 * 通过多线程的方式，不断地发送和读取
 * 输入流与输出流独立，在输出的同时可以输入
 */
public class MultiClient {
    private static Socket client;


    public static void main(String[] args) {
        try {
            client = new Socket("localhost", 8888);
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(new Send(client)).start();
        new Thread(new Receive(client)).start();
    }

}

