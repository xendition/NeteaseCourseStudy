package com.michael.study.io.bio;

import com.michael.study.io.IoConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 类功能描述:
 * <pre>
 *   BIO - 服务端 - 单线程处理(研究阻塞)
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/04/29 09:13
 */
public class BioServerSingleThread implements IoConstant {

    public static void main(String[] args) {

        try (
                ServerSocket serverSocket = new ServerSocket(port)
        ) {

            System.out.println("服务器启动成功,等待客户端发起连接");

            System.out.println(serverSocket.getReceiveBufferSize());
            // 设置 缓冲队列大小(默认50)
            serverSocket.setReceiveBufferSize(200);
            // System.out.println(serverSocket.getReceiveBufferSize());

            while (!serverSocket.isClosed()) {

                try (
                        // 阻塞等待客户端连接进入
                        Socket request = serverSocket.accept();
                        InputStream inputStream = request.getInputStream()
                ) {

                    System.out.println("接到连接：" + request.toString());

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));
                    String msg;

                    System.out.println("等待客户端 " + request.toString() + " 发送数据");

                    // 没有数据，阻塞等待客户端传输数据
                    while ((msg = reader.readLine()) != null) {
                        if (msg.length() == 0) {
                            break;
                        }
                        System.out.println(msg);
                    }
                    System.out.println("收到数据,来自：" + request.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
