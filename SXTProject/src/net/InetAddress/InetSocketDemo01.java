package net.InetAddress;

import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * 封装端口：在InetAddress基础上+端口
 */

public class InetSocketDemo01 {
    public static void main(String[] args) {
        InetSocketAddress isa = new InetSocketAddress("10.160.11.66", 9999);
        System.out.println(isa.getHostName());
        System.out.println(isa.getPort());//获取端口

        //通过getAddress获得一个InetAddress对象
        InetAddress add = isa.getAddress();
        System.out.println(add.getHostAddress());  //返回地址
        System.out.println(add.getHostName());   //返回计算机名|或本机名
    }

}
