package com.michael.study.io.bio;

import com.michael.study.io.IoConstant;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 类功能描述:
 * <pre>
 *   BIO - 服务端 - 线程池处理 - 简单实现HTTP协议
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/04/29 09:13
 */
public class BioServerThreadPoolHttp implements IoConstant {

    public static void main(String[] args) {

        try (
                ServerSocket serverSocket = new ServerSocket(port)
        ) {

            System.out.println("服务器启动成功,等待客户端发起连接");
            // 设置 缓冲队列大小(默认50)
            serverSocket.setReceiveBufferSize(200);

            while (!serverSocket.isClosed()) {

                // 阻塞等待客户端连接进入
                Socket request = serverSocket.accept();

                System.out.println("接到连接：" + request.toString());

                threadPool.execute(() -> {

                    try {
                        InputStream inputStream = request.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));
                        String msg;

                        System.out.println("等待客户端 " + request.toString() + " 发送数据");

                        while ((msg = reader.readLine()) != null) {
                            if (msg.length() == 0) {
                                break;
                            }
                            System.out.println(msg);
                        }
                        System.out.println("收到数据,来自：" + request.toString());

                        // 响应结果 200
                        OutputStream outputStream = request.getOutputStream();
                        String responseMsg = "Hello World I am Studying";
                        outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                        outputStream.write(("Content-Length: " + responseMsg.length() + "\r\n\r\n").getBytes());
                        outputStream.write(responseMsg.getBytes());
                        outputStream.flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            request.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
