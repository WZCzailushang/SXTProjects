package net.URL;

import java.io.*;
import java.net.URL;

/**
 * 获取资源：源代码
 */

public class URLDemo02 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");//访问的是主页，默认资源

        /**
         * 获取资源，网络流
         */

//        InputStream inputStream = url.openStream();
//
//        byte[] flush = new byte[1024];
//        int len = 0;
//        while ((len = inputStream.read(flush)) != -1) {
//            System.out.println(new String(flush, 0, len));
//        }
//        inputStream.close();

        /**
         * 指定解码字符集
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("baidu.html")));

        String msg = null;

        while ((msg = br.readLine()) != null) {
            System.out.println(msg);
            bw.write(msg);
        }
        bw.flush();
        bw.close();
        br.close();


    }
}
