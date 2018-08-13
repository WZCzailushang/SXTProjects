package net.tcp.Demo03;

import net.tcp.CloseUtil;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 客户端接收数据
 */

class Receive implements Runnable {


    /**
     * 客户端的输入流
     */
    private DataInputStream dis;
    private boolean isRunning = true;


    public Receive() {
    }

    /**
     * 获取客户端的输入流
     *
     * @param client
     */
    public Receive(Socket client) {

        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(dis);
        }
    }

    /**
     * 接收数据
     *
     * @return
     */
    public String receive() {
        String msg = "";
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(dis);
        }

        return msg;
    }


    @Override
    public void run() {
        while (isRunning) {
            System.out.println(receive());
        }

    }
}
