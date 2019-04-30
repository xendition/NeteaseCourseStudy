package com.michael.study.io.nio;

import java.nio.ByteBuffer;

/**
 * NIO的3个组成部分之 - Buffer(缓冲)简单使用+运行原理探究 实例
 * <p>Buffer可以理解为对数组的封装，使之更容易被使用
 * <p>Buffer中的重要属性有：容量（capacity）位置（position）限度（limit）
 *
 * @author Michael
 */
public class BufferDemo {

    public static void main(String[] args) {

        long start = System.nanoTime();

        // 构建一个byte字节缓冲区，容量是4 (堆内缓冲)
        ByteBuffer byteBuffer = ByteBuffer.allocate(4);

        /**
         * <pre>
         *  ByteBuffer.allocateDirect 会创建一个堆外缓冲
         *
         *  堆外缓冲的好处：
         *      1. 进行网络IO或者文件IO时比堆内缓冲少一次拷贝
         *          gc会移动对象内存，在file或者socket的过程中，JVM的实现会把数据先复制到堆外，再进行写入.
         *      2. 堆外缓冲在GC范围之外，降低了GC的压力，但是它实现了自动处理。
         *          DirectByteBuffer 中有一个 Cleaner 对象，Cleaner 被gc前会执行 clean 方法
         *          触发 DirectByteBuffer 中定义的 Deallocator
         *
         *      PS: 在性能可观的时候才去使用，分配给大型、长寿命(网络传输、文件传输读写场景)
         *          同时通过配置JVM参数MaxDirectMemorySize限制堆外内存的大小，避免整个机器的内存资源耗尽.
         *
         * </pre>
         */

        // ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4);

        // 默认写入模式，查看三个重要的指标
        System.out.println(String.format("初始化(默认为写模式)：capacity容量：%s, position位置：%s, limit限制：%s", byteBuffer.capacity(),
                                         byteBuffer.position(), byteBuffer.limit()
        ));
        // 写入3字节的数据
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 3);
        // 再看数据
        System.out.println(String.format("写入3字节后，capacity容量：%s, position位置：%s, limit限制：%s", byteBuffer.capacity(),
                                         byteBuffer.position(), byteBuffer.limit()
        ));

        // 转换为读取模式(不调用flip方法，也是可以读取数据的，但是position记录读取的位置不对)
        System.out.println("#######开始读取");
        byteBuffer.flip();
        System.out.println(String.format("转为读模式后，capacity容量：%s, position位置：%s, limit限制：%s", byteBuffer.capacity(),
                                         byteBuffer.position(), byteBuffer.limit()
        ));
        byte a = byteBuffer.get();
        System.out.println("读取第一个数据 -> " + a);
        byte b = byteBuffer.get();
        System.out.println("读取第二个数据 -> " + b);
        System.out.println(String.format("读取2字节数据后，capacity容量：%s, position位置：%s, limit限制：%s", byteBuffer.capacity(),
                                         byteBuffer.position(), byteBuffer.limit()
        ));

        // 继续写入3字节，此时读模式下，limit=3，position=2.继续写入只能覆盖写入一条数据
        // clear()方法清除整个缓冲区。compact()方法仅清除已阅读的数据。转为写入模式
        byteBuffer.compact(); // buffer : 1 , 3
        System.out.println(
                String.format("compact(清除已阅读数据)后，capacity容量：%s, position位置：%s, limit限制：%s", byteBuffer.capacity(),
                              byteBuffer.position(), byteBuffer.limit()
                ));
        byteBuffer.put((byte) 3);
        byteBuffer.put((byte) 4);
        byteBuffer.put((byte) 5);
        System.out.println(String.format("再次写入3字节数据后，capacity容量：%s, position位置：%s, limit限制：%s", byteBuffer.capacity(),
                                         byteBuffer.position(), byteBuffer.limit()
        ));
        byteBuffer.clear();
        System.out.println(
                String.format("clear(清除整个缓冲区)后，capacity容量：%s, position位置：%s, limit限制：%s", byteBuffer.capacity(),
                              byteBuffer.position(), byteBuffer.limit()
                ));

        long end = System.nanoTime();

        /**
         * 1s = 1000ms
         * 1ms = 1000μs
         * 1μs = 1000ns
         * 1ns = 1000ps
         */
        System.out.println("总共耗时:" + (end - start) + "纳秒");

        // rewind() 重置position为0
        // mark() 标记position的位置
        // reset() 重置position为上次mark()标记的位置
    }
}
