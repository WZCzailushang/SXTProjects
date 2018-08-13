package net.InetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress没有封装端口
 */

public class InetDemo01 {
    public static void main(String[] args) throws UnknownHostException {
        //使用getLocalHost方法创建InetAddress对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost.getHostAddress());//返回本机ip
        System.out.println(localHost.getHostName());//输出计算机名

        //根据域名得到InetAddress对象
        localHost = InetAddress.getByName("www.163.com");
        System.out.println(localHost.getHostAddress());//返回163服务器的ip：
        System.out.println(localHost.getHostName());//返回域名
//
//        根据ip得到InetAddress对象
//        如果ip地址存在并且DNS给予解析，那么getHostName()返回的就是域名
//        如果ip地址不存在或者DNS不给解析，那么返回的就是自身
        localHost = InetAddress.getByName("");
        System.out.println(localHost.getHostAddress());
        System.out.println(localHost.getHostName());

    }
}
