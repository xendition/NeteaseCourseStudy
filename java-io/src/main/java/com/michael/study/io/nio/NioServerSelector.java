package com.michael.study.io.nio;

import com.michael.study.io.IoConstant;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * 类功能描述:
 * <pre>
 *   NIO 服务端 使用 Selector + channel 实现
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/04/29 20:58
 */
public class NioServerSelector implements IoConstant {

    private Selector selector;
    private ServerSocketChannel serverChannel;

    /**
     * 初始化NIO服务器
     */
    private void init() throws IOException {

        // 创建一个Selector
        this.selector = Selector.open();
        // 获取 ServerSocket 通道
        this.serverChannel = ServerSocketChannel.open();
        // 设置为非阻塞
        this.serverChannel.configureBlocking(false);
        // 端口绑定
        this.serverChannel.bind(new InetSocketAddress(port));
        // 有连接访问进来，Selector 选中
        this.serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * 启动服务
     */
    private void doService() throws IOException {

        System.out.println("NIO服务器启动，端口：" + port);

        // 连接计数
        int connectionCount = 0;

        // CPU核数
        int cpuCoreCout = Runtime.getRuntime().availableProcessors();

        // 少量的线程
        int poolSize = cpuCoreCout - 1;

        ExecutorService threadPool = new ThreadPoolExecutor(
                poolSize, poolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                r -> new Thread(r)
        );

        while (true) {

            // 阻塞等待就绪的事件 select()方法为阻塞方法，此处不会导致CPU空转
            int readyCount = this.selector.select();
            // select()阻塞可能被中断，中断后返回值为0
            if (readyCount == 0) {
                continue;
            }

            // 遍历已经就绪的客户端 channel
            Iterator<SelectionKey> keyIterator = this.selector.selectedKeys().iterator();
            while (keyIterator.hasNext()) {

                SelectionKey key = keyIterator.next();

                // 删除已选的key，防止重复处理
                keyIterator.remove();

                // 客户端请求连接事件
                if (key.isAcceptable()) {
                    // 获得与客户端连接的通道
                    SocketChannel clientChannel = this.serverChannel.accept();
                    // 设置非阻塞
                    clientChannel.configureBlocking(false);
                    // 注册到selector 监听读就绪事件
                    clientChannel.register(this.selector, SelectionKey.OP_READ, ++connectionCount);

                    System.out.println("接到新的请求连接 -> " + clientChannel);

                } else if (key.isReadable()) {
                    System.out.println("数据可以进行读取了");
                    // 数据可以进行读取了，交给线程池处理
                    threadPool.execute(new ProcessReadable(key));
                    key.interestOps(SelectionKey.OP_WRITE);
                } else if (key.isWritable()) {
                    System.out.println("数据可以进行写入了");
                    // 数据可以进行写入了，交给线程池处理
                    threadPool.execute(new ProcessWritable(key));
                    key.cancel();
                }
            }
        }
    }

    class ProcessReadable implements Runnable {

        private SelectionKey key;

        public ProcessReadable(SelectionKey key) {
            this.key = key;
        }

        @Override
        public void run() {

            try {
                System.out.println("连接 [" + this.key.attachment() + "] 发来了信息：\n" + this.readFromChannel());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String readFromChannel() throws IOException {

            SocketChannel channel = (SocketChannel) this.key.channel();

            // 1024
            int bufferSize = 1 << 10;
            ByteBuffer buffer = ByteBuffer.allocateDirect(bufferSize);

            // 定义一个更大的Buffer
            ByteBuffer bigBuffer = null;

            int count = 0;
            // 当前读取的数量 read 返回 0 或者 -1 表示已经读完
            int readCount;
            while (channel.isOpen() && (readCount = channel.read(buffer)) != 0 && readCount != -1) {
                count++;
                ByteBuffer tempBuffer = ByteBuffer.allocateDirect(bufferSize * (count + 1));

                if (bigBuffer != null) {
                    // 将buffer由写模式转为读模式
                    bigBuffer.flip();
                    tempBuffer.put(bigBuffer);
                }

                bigBuffer = tempBuffer;

                // 将本次读到的数据放入BigBuffer中
                buffer.flip();
                bigBuffer.put(buffer);

                // 为下一次读，清理buffer
                buffer.clear();
            }

            if (bigBuffer != null) {

                bigBuffer.flip();

                try {
                    return decoder.decode(bigBuffer).toString();
                } catch (CharacterCodingException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return null;
        }
    }

    class ProcessWritable implements Runnable {

        private SelectionKey key;

        public ProcessWritable(SelectionKey key) {
            this.key = key;
        }

        @Override
        public void run() {

            String msg = "Hello World ~ I am Studying " + this.key.attachment();

            // 响应结果 200
            String response = "HTTP/1.1 200 OK\r\n" +
                              "Content-Length: " + msg.length() + "\r\n\r\n" + msg;
            ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
            SocketChannel channel = (SocketChannel) this.key.channel();
            while (buffer.hasRemaining()) {
                try {
                    channel.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 启动一个NIO服务器
     */
    public static void start() throws IOException {

        NioServerSelector server = new NioServerSelector();
        server.init();
        server.doService();
    }

    public static void main(String[] args) throws Exception {
        start();
    }
}
