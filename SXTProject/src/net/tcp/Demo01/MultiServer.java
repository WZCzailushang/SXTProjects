package net.tcp.Demo01;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端：接收来自客户端的信息并发送回去
 *此服务器端没有多线程，每次只能与一个客户端建立连接，等这个客户端停止连接后才能连接下一个客户端
 *
 */
public class MultiServer {

    private static BufferedReader br;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket accept = serverSocket.accept();

        while (true) {
            DataInputStream dis = new DataInputStream(accept.getInputStream());
            String s = dis.readUTF();
            System.out.println("服务器接收-->" + s);
//            br = new BufferedReader(new InputStreamReader(System.in));
//            String msg = br.readLine();
            DataOutputStream dos = new DataOutputStream(accept.getOutputStream());
            dos.writeUTF("服务器发送-->" + s);
            dos.flush();
        }

    }

}


