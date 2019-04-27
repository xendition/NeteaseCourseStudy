package com.michael.study.base.thread.state;

import java.util.concurrent.locks.LockSupport;

/**
 * 类功能描述:
 * <pre>
 *   多线程运行状态切换示例
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/04/26 12:22
 */
public class ThreadStateDemo {


    public static void main(String[] args) throws Exception {

        // lifecycle1();
        // lifecycle2();
        // lifecycle3();
        lifecycle4();

    }

    /**
     * 功能描述:
     * 〈线程生命周期 —— NEW -> RUNNABLE -> TERMINATED〉
     *
     * @author Michael
     * @date 2019/04/26
     */
    public static void lifecycle1() throws Exception {

        System.out.println("\n#######第一种状态切换：新建 -> 运行 -> 终止 ################################");

        Thread thread = new Thread(() -> {
            System.out.println("进入线程执行方法run(),thread 当前状态：" + Thread.currentThread().getState().toString());
            System.out.println("thread 执行完毕");
        });

        System.out.println("new Thread() 还没 start(),thread当前状态：" + thread.getState().toString());

        thread.start();

        // 等待thread执行结束，再看状态
        Thread.sleep(200L);

        System.out.println("等待200毫秒(子线程应该已经执行完毕)，再看thread当前状态：" + thread.getState().toString());
        System.out.println();

        // thread.start(); TODO 注意，线程终止之后，再进行调用，会抛出IllegalThreadStateException异常
    }

    /**
     * 功能描述:
     * 〈线程生命周期 —— NEW -> RUNNABLE -> TIMED_WAITING -> RUNNABLE -> TERMINATED〉
     *
     * @author Michael
     * @date 2019/04/26
     */
    public static void lifecycle2() throws Exception {
        
        System.out.println("############第二种：新建 -> 运行 -> 等待(定时) -> 运行 -> 终止(sleep方式)###########################");

        Thread thread = new Thread(() -> {
            try {
                System.out.println("thread 线程进行 sleep(500)");
                // 将线程2移动到等待状态，1500后自动唤醒
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread 睡醒了,thread当前状态：" + Thread.currentThread().getState().toString());
            System.out.println("thread 执行完毕");
        });
        System.out.println("new Thread() 还没 start(),thread当前状态：" + thread.getState().toString());
        thread.start();
        System.out.println("调用start()，thread当前状态：" + thread.getState().toString());
        // 等待200毫秒，再看状态
        Thread.sleep(200L);
        System.out.println("等待200毫秒(thread 现在应该还在睡觉)，再看thread当前状态：" + thread.getState().toString());
        // 再等待500毫秒，让thread执行完毕，再看状态
        Thread.sleep(500L);
        System.out.println("等待500毫秒(thread 现在应该已经执行完毕了)，再看thread当前状态：" + thread.getState().toString());
        System.out.println();
    }

    /**
     * 功能描述:
     * 〈线程生命周期 —— NEW -> RUNNABLE -> BLOCKED -> RUNNABLE -> TERMINATED〉
     *
     * @author Michael
     * @date 2019/04/26
     */
    public static void lifecycle3() throws Exception {

        System.out.println("############第三种：新建 -> 运行 -> 阻塞 -> 运行 -> 终止###########################");
        Thread thread = new Thread(() -> {
            synchronized (ThreadStateDemo.class) {
                System.out.println("进入线程执行方法run(),thread当前状态：" + Thread.currentThread().getState().toString());
                System.out.println("thread 执行完毕");
            }
        });

        synchronized (ThreadStateDemo.class) {
            System.out.println("new Thread() 还没 start(),thread当前状态：" + thread.getState().toString());
            thread.start();
            System.out.println("调用start方法，thread当前状态：" + thread.getState().toString());
            // 等待200毫秒，再看状态
            Thread.sleep(200L);
            System.out.println("等待200毫秒，再看thread当前状态：" + thread.getState().toString());
        }
        // 再等待1秒，让thread执行完毕，再看状态
        Thread.sleep(1000L);
        System.out.println("等待1秒，让thread抢到锁，再看thread当前状态：" + thread.getState().toString());
        System.out.println();
    }

    /**
     * 功能描述:
     * 〈线程生命周期 —— NEW -> RUNNABLE -> WAITING -> RUNNABLE -> TERMINATED〉
     *
     * @author Michael
     * @date 2019/04/26
     */
    public static void lifecycle4() throws Exception {

        System.out.println("############第四种：新建 -> 运行 -> 等待 -> 运行 -> 终止###########################");

        Thread thread4 = new Thread(() -> {
            System.out.println("进入线程执行方法run(),thread4当前状态：" + Thread.currentThread().getState().toString());
            LockSupport.park();
            System.out.println("thread4 睡醒了,thread4当前状态：" + Thread.currentThread().getState().toString());
            System.out.println("thread4 执行完毕");
        });

        System.out.println("new Thread() 还没 start(),thread4当前状态：" + thread4.getState().toString());
        thread4.start();
        System.out.println("调用start()，thread4当前状态：" + thread4.getState().toString());

        // 等待200毫秒，再看状态
        Thread.sleep(200L);
        System.out.println("等待200毫秒(thread4 现在应该在睡觉)，再看thread4当前状态：" + thread4.getState().toString());
        LockSupport.unpark(thread4);

        Thread.sleep(1000L);
        System.out.println("等待1秒(thread4 现在应该已经执行完毕了)，再看thread4当前状态：" + thread4.getState().toString());
        System.out.println();
    }

}
