package net.tcp.Demo01;

import net.tcp.CloseUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class Send implements Runnable {


    /**
     * 从控制台输入并且发送到服务器
     */

    /**
     * 控制台输出流
     */
    private BufferedReader br;
    /**
     * 管道的输出流
     */
    private DataOutputStream dos;

    /**
     * 判断线程是否存活
     */
    private boolean isRunning = true;

    public Send() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * 获得客户端Socket的输出流
     *
     * @param client
     */
    public Send(Socket client) {
        this();
        try {
            dos = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            /**
             * 出现异常 判断线程死亡 并且关闭所有流
             */
            isRunning = false;
            CloseUtil.closeAll(br, dos);
        }
    }

    private String getMsgFromSystem() {
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void send() throws IOException {
        String s = getMsgFromSystem();
        if (null != s && !"".equals(s)) {
            dos.writeUTF(s);
            dos.flush();
        }
    }


    @Override
    public void run() {
        while (isRunning) {
            System.out.println("Send 线程");
            try {
                send();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
