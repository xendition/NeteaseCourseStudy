package com.michael.study.performance.fullgc;

/**
 * 类功能描述:
 * <pre>
 *   导致 FullGC 的原因展示示例：
 *
 *   JVM参数：
 *      输出到Console
 *          -server -Xms512m -Xmx512m -verbose:gc -XX:+PrintGCDetails -XX:+PrintGCDateStamps
 *      输出到文件 -Xloggc:d:\gc.log
 *          -server -Xms512m -Xmx512m -verbose:gc -XX:+PrintGCDetails -Xloggc:文件路径 -XX:+PrintGCDateStamps
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/30 21:36
 */
public class FullGcDemo001 {

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            // 256M
            byte[] tmp = new byte[1024 * 1024 * 256];
            // System.gc(); // 8G堆 128兆。full GC
            // System.out.println("我GC一次了");
            Thread.sleep(1000L);
        }
    }
}
