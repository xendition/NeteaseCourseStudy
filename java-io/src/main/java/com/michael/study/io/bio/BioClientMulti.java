package com.michael.study.io.bio;

import com.michael.study.io.IoConstant;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

/**
 * 类功能描述:
 * <pre>
 *   BIO - 客户端 - 启动多个
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/04/29 08:44
 */
public class BioClientMulti implements IoConstant {

    private static int clientCount = 99;

    public static void main(String[] args) {

        for (int i = 0; i < clientCount; i++) {
            int n = i;
            new Thread(() -> {
                try (
                        Socket socket = new Socket(host, port);
                        OutputStream out = socket.getOutputStream()
                ) {

                    String msg = n + " --> " + UUID.randomUUID().toString();

                    // 阻塞，写完成
                    out.write(msg.getBytes(charset));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
