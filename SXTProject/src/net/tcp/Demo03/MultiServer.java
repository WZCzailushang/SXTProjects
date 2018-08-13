package net.tcp.Demo03;

import net.tcp.CloseUtil;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 服务器端：接收来自客户端的信息并发送回去
 * 加入多线程 使得服务器可以同时与多个客户端建立连接
 * <p>
 * 加入了名称 可以显示是哪个人进入了聊天室
 *增加了name属性，可以分辨各个客户端
 * 增加了私聊功能，可以通过分析客户端传来的@(用户名):  达到使两个客户端实现单独通信的功能
 * 增加了是系统消息还是用户发送消息的判断
 */
public class MultiServer {

    private static BufferedReader br;
    private List<MyChannel> all = new ArrayList<MyChannel>();

    public static void main(String[] args) throws IOException {
        new MultiServer().start();
    }

    private void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            Socket accept = serverSocket.accept();
            MyChannel channel = new MyChannel(accept);
            all.add(channel);
            new Thread(channel).start();
        }
    }

    private class MyChannel implements Runnable {

        private DataInputStream dis;
        private DataOutputStream dos;
        private String name;
        private boolean isRunnning = true;

        public MyChannel() {
        }

        public MyChannel(Socket client) {
            this();
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());

                this.name = dis.readUTF();
                send("欢迎进入聊天室");
                sendOthers(this.name + "进入了聊天室", true);
            } catch (IOException e) {
                isRunnning = false;
                CloseUtil.closeAll(dis, dos);
                all.remove(this);
            }
        }

        /**
         * 将接受到的信息发送给其他客户端，自己除外
         */
        public void sendOthers(String msg, boolean flag) {
            if (msg.startsWith("@") && msg.contains(":")) {
                String name = msg.substring(1, msg.indexOf(":"));
                String context = msg.substring(msg.indexOf(":") + 1);
                for (MyChannel other : all) {
                    if (other == this) {
                        continue;
                    }
                    if (other.name.equals(name)) {
                        other.send(this.name + "悄悄对您说:" + context);

                    }
                }

            } else {
                for (MyChannel other : all) {

                    /**
                     * 自己不发送给自己
                     */
                    if (other == this) {
                        continue;
                    }
                    /**
                     * 此处分辨是客户端发送的消息还是系统消息
                     */
                    System.out.println(this.name + "进入了聊天室，时间为:" + new Date());
                    if (!flag) {
                        other.send(this.name + "对所有人说：" + msg);
                    } else {
                        other.send("系统消息：" + msg);
                    }
                }
            }
        }

        public String receive() {
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                isRunnning = false;
                CloseUtil.closeAll(dis);
                all.remove(this);
            }
            return msg;
        }

        public void send(String msg) {
            if (null == msg && msg.equals("")) {
                return;
            }
            try {
                dos.writeUTF(msg);
                dos.flush();
            } catch (IOException e) {
                isRunnning = false;
                CloseUtil.closeAll(dos);
                all.remove(this);
            }
        }


        @Override
        public void run() {
            while (isRunnning) {
                sendOthers(receive(), false);
            }
        }
    }

}


