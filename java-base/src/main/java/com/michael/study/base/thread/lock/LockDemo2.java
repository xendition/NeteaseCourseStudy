package com.michael.study.base.thread.lock;

/**
 * 类功能描述:
 * <pre>
 *   自定义锁的错误示范：会有线程一直在等待
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/15 10:02
 */
public class LockDemo2 {

    DemoLock2 lock = new DemoLock2();

    int i = 0;

    public void incr() {

        // 一个线程拿到锁，其它线程会进入等待
        lock.lock();

        try {
            i++;
        } finally {
            // 处理完业务逻辑，释放锁
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        LockDemo2 lockDemo1 = new LockDemo2();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    lockDemo1.incr();
                }
            }).start();
        }

        Thread.sleep(2000L);

        System.out.println(lockDemo1.i);

    }
}
