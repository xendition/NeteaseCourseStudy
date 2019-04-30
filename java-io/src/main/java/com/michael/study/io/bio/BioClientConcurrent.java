package com.michael.study.io.bio;

import com.michael.study.io.IoConstant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * 类功能描述:
 * <pre>
 *   BIO - 客户端 - 并发启动
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/04/29 10:53
 */
public class BioClientConcurrent implements IoConstant {

    private static int clientCount = 50000;

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(clientCount);

        for (int i = 0; i < clientCount; i++) {
            new Thread(new SocketProcess(countDownLatch, i)).start();
        }
        // 等待所有线程处理完成
        countDownLatch.await();

        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    static class SocketProcess implements Runnable {

        CountDownLatch countDownLatch;

        private int i;

        public SocketProcess(CountDownLatch countDownLatch, int i) {
            this.countDownLatch = countDownLatch;
            this.i = i;
        }

        @Override
        public void run() {
            try (
                    Socket socket = new Socket(host, port);
                    // 客户端发送的信息
                    OutputStream out = socket.getOutputStream();
                    // 接收服务端发送过来的信息
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream(), charset)
                    )
            ) {
                String line = String.format(
                        "来自客户端[%s]的信息 -> hello %s, My name is Michael",
                        Thread.currentThread().getId(),
                        this.i
                );
                out.write(line.getBytes(charset));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                this.countDownLatch.countDown();
            }
        }
    }
}
