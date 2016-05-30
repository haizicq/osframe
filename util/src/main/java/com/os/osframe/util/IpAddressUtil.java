package com.os.osframe.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by wangdc on 2015-4-30.
 */
public class IpAddressUtil {
    //IP地址对象
    private static InetAddress netAddress=null;
    public static InetAddress getInetAddress(){
        if(netAddress==null){
            try{
                netAddress= InetAddress.getLocalHost();
            }catch(UnknownHostException e){
                System.out.println("unknown host!");
            }
        }
        return netAddress;
    }

    public static String getHostIp(){
        if(null == getInetAddress()){
            return null;
        }
        String ip = getInetAddress().getHostAddress(); //get the ip address
        return ip;
    }

    public static String getHostName(){
        if(null == getInetAddress()){
            return null;
        }
        String name = getInetAddress().getHostName(); //get the host address
        return name;
    }

}
