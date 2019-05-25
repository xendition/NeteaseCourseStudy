package com.michael.study.base.thread.jmm;

import java.util.concurrent.TimeUnit;

/**
 * 类功能描述:
 * <pre>
 *   线程可见性DEMO
 *
 *   可见性问题概述：多个线程看到的数据不一致
 *
 *   可见性问题原因：
 *      1. 由于CPU高速缓存/本地变量表等原因导致的可见性，由于CPU高速缓存遵循缓存一致性协议，只会导致短时间的可见性问题
 *      2. 指令重排序：
 *              JAVA编程语言的语义允许编译器和处理器执行优化，这些优化可以与不正确的同步代码交互，从而产生看似矛盾的行为。
 *
 *   JVM运行模式：
 *      1. 编译 - 字节码 --- jit提前编译  - 汇编
 *      2. 解释 - 字节码 –-- 一段段编译   – 汇编
 *      3. 混合(默认的模式) - 运行的过程中，JIT编译器生效，针对热点代码进行优化
 *
 *
 *   使用client模式可以解决问题 -client (PS:64位的JDK没有这个选项)
 *   关闭JIT优化可以解决问题 -Djava.compiler=NONE
 *
 *   使用volatile(禁止重排序)
 *
 *   JVM运行时数据区的设计，多个内存之间进行交互势必会有问题，JAVA语言提出了内存模型的相关规范
 *
 *   内存模型的含义
 *      1. 内存模型描述程序的可能行为
 *      2. JAVA编程语言内存模型通过检查执行跟踪中的每个读操作，并根据某些规则检查该读操作观察到的写操作是否有效来工作
 *      3. 只要程序操作中的所有执行生产的结果都可以由内存模型来预测，具体的实现者任意实现，包括操作的重排序与删除不必要的同步
 *
 *      内存模型决定了程序在每个点上可以读到什么值
 *
 *   Shared Variables 共享变量描述
 *      1. 可以在线程之前共享的内存称为共享内存/堆内存
 *      2. 所有实例字段、静态字段和数组元素都存储在堆内存中
 *
 *      只要有一个对共享内存中的变量的访问是写的，对该变量的第二个读/写是冲突的(只能同时读，不能同时写/读写)
 *
 *   线程操作的定义：
 *      1. write 要写的变量以及要写的值
 *      2. read 要读的变量以及可见的写入值
 *      3. lock 要锁定的管程(监视器monitor)
 *      4. unlock 要解锁的管程
 *      5. 外部操作(socket等)
 *      6. 启动和终止
 *
 *   对于同步的规则定义：
 *      1. 对于监视器m的解锁与所有的后续操作对于m的加锁同步
 *      2. 对于volatile 变量v的写入，与所有其它线程后续对v的读同步
 *      3. 启动线程的操作与线程中的第一个操作同步
 *      4. 对于每个属性写入默认值 0 false null 与每个线程对其进行的操作同步
 *      5. 线程T1的最后操作与线程T2发现线程T1已经结束同步。(isAlive join 可以判断线程是否终结)
 *      6. 如果线程T1中断了T2，那么线程T1的中断操作与其它所有线程发现T2被中断了同步
 *          通过抛出InterrupedException异常，或者调用Thread.interrupted或者Thread.isInterupted
 *
 *
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/07 09:06
 */
public class VisibilityDemo {

    private volatile boolean flag = true;

    private volatile int v = 1;

    public static void main(String[] args) throws InterruptedException {


        VisibilityDemo demo = new VisibilityDemo();


        Thread thread1 = new Thread(() -> {
            System.out.println("thread1");
            demo.v = 3;
        });
        Thread thread2 = new Thread(() -> {
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(demo.v);
        });

        thread1.start();
        thread2.start();



        System.out.println("start");

        Thread thread = new Thread(() -> {
            int i = 0;
            while (demo.flag) {
                i++;
            }
            /**
             * JIT 优化后会大概变成以下类似的代码
             *  boolean f = demo.flag
             *  if (f) {
             *      while(true){
             *          i++;
             *      }
             *  }
             */
            System.out.println(i);
        });

        thread.start();

        TimeUnit.SECONDS.sleep(1);

        demo.flag = false;

        System.out.println("flag is false");
    }
}
