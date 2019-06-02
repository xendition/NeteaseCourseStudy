package com.study.test;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * 类功能描述:
 * <pre>
 *   高性能学习考核 - 用 NIO 解析百度服务器类型
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/02 13:13
 */
public class TEST402235690 {

    @Test
    public void printBaiduServerType() throws IOException {
        System.out.println();
        System.out.println(this.getBaiduServerType());
        System.out.println();
    }

    private String getBaiduServerType() throws IOException {

        String host = "www.baidu.com";
        int port = 80;

        SocketAddress socketAddress = new InetSocketAddress(host, port);

        try (
                SocketChannel channel = SocketChannel.open();
                Selector selector = Selector.open()
        ) {

            channel.configureBlocking(false);
            channel.connect(socketAddress);

            channel.register(selector, SelectionKey.OP_WRITE);

            while (!channel.finishConnect()) {
                // 没连接上,则一直等待
                Thread.yield();
            }

            while (true) {

                // 阻塞等待就绪的事件 select()方法为阻塞方法，此处不会导致CPU空转
                int readyCount = selector.select();
                // select()阻塞可能被中断，中断后返回值为0
                if (readyCount == 0) {
                    continue;
                }

                // 遍历已经就绪的客户端 channel
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {

                    SelectionKey key = keyIterator.next();
                    // 删除已选的key，防止重复处理
                    keyIterator.remove();

                    if (key.isWritable()) {

                        //发送HTTP请求 - 模拟chrome
                        String httpRequest = "GET / HTTP/1.1\r\n"
                                             + "Connection: keep-alive\r\n"
                                             + "Upgrade-Insecure-Requests: 1\r\n"
                                             +
                                             "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                                             + "(KHTML, like "
                                             + "Gecko) Chrome/74.0.3729.169 Safari/537.36\r\n"
                                             +
                                             "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,"
                                             + "image/apng,"
                                             + "*/*;q=0.8,application/signed-exchange;v=b3\r\n"
                                             + "Accept-Encoding: gzip, deflate, br\r\n"
                                             + "Accept-Language: zh-CN,zh;q=0.9\r\n\r\n\r\n";

                        channel.write(ByteBuffer.wrap(httpRequest.getBytes(Charset.forName("UTF-8"))));

                        key.interestOps(SelectionKey.OP_READ);
                    } else if (key.isReadable()) {

                        String result = this.getBaiduServerType0(channel);
                        key.cancel();

                        return "我的QQ号：402235690，我的解析到百度服务器server类型是：" + result;
                    }
                }
            }
        }
    }

    private String getBaiduServerType0(SocketChannel channel) throws IOException {

        // 1024
        int bufferSize = 1 << 10;
        ByteBuffer buffer = ByteBuffer.allocateDirect(bufferSize);

        while (channel.isOpen() && channel.read(buffer) > 0) {

            // 将buffer由写模式转为读模式
            buffer.flip();
            String responsePart = Charset.forName("UTF-8").decode(buffer).toString();

            if (responsePart.contains("Server:")) {

                String[] arr = responsePart.split("\r\n");

                for (String s : arr) {
                    if (s.contains("Server:")) {
                        return s;
                    }
                }
            }
        }
        return null;
    }
}
