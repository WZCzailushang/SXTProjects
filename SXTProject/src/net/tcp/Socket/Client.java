package net.tcp.Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 1、创建客户端 必须指定服务器+端口  此时就在连接
 * Socket(String host, int port)
 * 客户端不用再指定端口，有系统内部自行分配
 * 创建一个流套接字并将其连接到指定主机上的指定端口号。
 * <p>
 * UDP:即使没有服务器，也可以发送数据，只不过数据丢失了，不会抛异常
 * <p>
 * 2、接收客户端连接
 * <p>
 * 3、发送数据+接收数据
 */


public class Client {
    public static void main(String[] args) throws IOException {
        //1、创建客户端 必须指定服务器+端口 此时就在连接
        //只有在服务器起来之后，才能建立连接，否则会抛出异常
        Socket client = new Socket("localhost", 8888);

        //2、接收数据
//        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
//        String echo  =br.readLine();

        DataInputStream dis = new DataInputStream(client.getInputStream());
        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
        while (true) {
           dos.writeUTF("客户端的信息");
           dos.flush();
        }

    }

}
