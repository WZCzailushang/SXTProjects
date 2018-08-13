package net.tcp.Socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 必须先启动服务器，再启动客户端
 * 1、创建服务器  指定端口  ServerSocket(int port)
 * 2、接收客户端连接
 * 3、发送数据+接收数据
 */

public class Server {
    public static void main(String[] args) throws IOException {
        //1、创建服务器  指定端口 ServerSocket(int port)
        ServerSocket server = new ServerSocket(8888);
        //2、接收客户端连接   阻塞式   如果没有接收到客户端，代码就不会往下执行
        Socket socket = server.accept();
        System.out.println("一个客户端建立连接");
        //3、发送数据
//        String msg = "欢迎使用";
//        //输出流
////        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
////
////        bw.write(msg);
////        bw.newLine();//要加一个换行符
////        bw.flush();
//
//        DataOutputStream ds = new DataOutputStream(socket.getOutputStream());
//        ds.writeUTF(msg);
//        ds.flush();

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        while (true){
            String s = dis.readUTF();
            System.out.println(s);
        }
        /**
         * 这里不用关，关了就把道路关了
         */


    }
}
