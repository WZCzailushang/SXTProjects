package net.URL;

import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo01 {
    /**
     * @param args
     * @throws MalformedURLException 定义的格式不对
     */
    public static void main(String[] args) throws MalformedURLException {
        /**
         * /后的为资源，#为锚点，是对内的，?是与用户交互的信息
         * 绝对路径构建
         */
        URL url = new URL("http://www.baidu.com:80/index.html#aa?");
        System.out.println("协议：" + url.getProtocol());//协议
        System.out.println("域名：" + url.getHost());//主机名
        System.out.println("端口：" + url.getPort());//端口
        System.out.println("资源：" + url.getFile());
        System.out.println("相对路径：" + url.getPath());
        System.out.println("锚点：" + url.getRef());//锚点
        System.out.println("参数：" + url.getQuery());//参数：存在锚点，返回null，不存在，返回正确


        /**
         * 相对路径
         */
        url = new URL("http://www.baidu.com:80/a/");
        url = new URL(url, "b.txt");
        System.out.println(url.toString());


    }
}
