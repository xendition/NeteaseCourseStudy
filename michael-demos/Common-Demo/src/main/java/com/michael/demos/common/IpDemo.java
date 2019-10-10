package com.michael.demos.common;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/9/15 9:51
 */
public class IpDemo {

    public static void main(String[] args) throws UnknownHostException {

        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }
}
