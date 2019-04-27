package com.michael.study.base.thread.state;

import java.util.concurrent.locks.LockSupport;

/**
 * 类功能描述:
 * <pre>
 *   线程通信 DEMO
 *   三种线程协作通信的方式：suspend/resume、wait/notify、park/unpark
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/04/27 12:24
 */
public class ThreadCommunicationDemo {

    /** 包子店 */
    public static Object baozidian = null;


    public static void main(String[] args) throws Exception {

        ThreadCommunicationDemo demo = new ThreadCommunicationDemo();

        // 对调用顺序有要求，也要开发自己注意锁的释放。这个被弃用的API， 容易死锁，也容易导致永久挂起。
        // demo.suspendResumeOK();
        // demo.suspendResumeDeadLock1();
        // demo.suspendResumeDeadLock2();

        // wait/notify 要求再同步关键字里面使用，免去了死锁的困扰，但是一定要先调用wait，再调用notify，否则永久等待了
        // demo.waitNotifyOK();
        // demo.waitNotifyDeadLock();
        //
        // park/unpark 没有顺序要求，但是park并不会释放锁，所有再同步代码中使用要注意
        // demo.parkUnparkOK();
        demo.parkUnparkDeadLock();

    }

    /** 正常的suspend/resume */
    public void suspendResumeOK() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            // 如果没包子，则进入等待
            while (baozidian == null) {
                System.out.println("1、进入等待 - suspend()");
                Thread.currentThread().suspend();
            }

            System.out.println("2、成功买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(1000L);
        baozidian = new Object();
        consumerThread.resume();
        System.out.println("3、通知消费者 - resume()");
    }

    /** 死锁的suspend/resume。 suspend并不会像wait一样释放锁，故此容易写出死锁代码 */
    public void suspendResumeDeadLock1() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            // 如果没包子，则进入等待
            while (baozidian == null) {
                System.out.println("1、进入等待");
                // 当前线程拿到锁，然后挂起
                synchronized (this) {
                    // suspend 不会释放锁
                    Thread.currentThread().suspend();
                }
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(1000L);
        baozidian = new Object();
        // 争取到锁以后，再恢复consumerThread
        synchronized (this) {
            consumerThread.resume();
        }
        System.out.println("3、通知消费者");
    }

    /** 导致程序永久挂起的suspend/resume 顺序不能乱 */
    public void suspendResumeDeadLock2() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            while (baozidian == null) {
                System.out.println("1、没包子，进入等待");
                try { // 为这个线程加上一点延时
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 这里的挂起执行在resume后面
                Thread.currentThread().suspend();
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(500L);
        baozidian = new Object();
        consumerThread.resume();
        System.out.println("3、通知消费者");

        // consumerThread.join();
    }

    /** 正常的wait/notify */
    public void waitNotifyOK() throws Exception {
        // 启动线程
        new Thread(() -> {
            synchronized (this) {
                // 如果没包子，则进入等待
                while (baozidian == null) {
                    try {
                        System.out.println("1、进入等待");
                        // wait 会释放锁
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("2、买到包子，回家");
        }).start();
        // 3秒之后，生产一个包子
        Thread.sleep(1000L);
        baozidian = new Object();
        synchronized (this) {
            this.notifyAll();
            System.out.println("3、通知消费者");
        }
    }

    /** 会导致程序永久等待的wait/notify 需要顺序保证 先 wait() 再 notifyAll */
    public void waitNotifyDeadLock() throws Exception {
        // 启动线程
        new Thread(() -> {
            // 如果没包子，则进入等待
            while (baozidian == null) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                synchronized (this) {
                    try {
                        System.out.println("1、进入等待");
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("2、买到包子，回家");
        }).start();
        // 3秒之后，生产一个包子
        Thread.sleep(500L);
        baozidian = new Object();
        synchronized (this) {
            this.notifyAll();
            System.out.println("3、通知消费者");
        }
    }

    /** 正常的park/unpark */
    public void parkUnparkOK() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            // 如果没包子，则进入等待
            while (baozidian == null) {
                System.out.println("1、进入等待");
                LockSupport.park();
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(1000L);
        baozidian = new Object();
        LockSupport.unpark(consumerThread);
        System.out.println("3、通知消费者");
    }

    /** 死锁的park/unpark */
    public void parkUnparkDeadLock() throws Exception {
        // 启动线程
        Thread consumerThread = new Thread(() -> {
            // 如果没包子，则进入等待
            while (baozidian == null) {
                System.out.println("1、进入等待");
                // 当前线程拿到锁，然后挂起
                synchronized (this) {
                    // 并不会释放锁
                    LockSupport.park();
                }
            }
            System.out.println("2、买到包子，回家");
        });
        consumerThread.start();
        // 3秒之后，生产一个包子
        Thread.sleep(1000L);
        baozidian = new Object();
        // 争取到锁以后，再恢复consumerThread
        synchronized (this) {
            LockSupport.unpark(consumerThread);
        }
        System.out.println("3、通知消费者");
    }

}
