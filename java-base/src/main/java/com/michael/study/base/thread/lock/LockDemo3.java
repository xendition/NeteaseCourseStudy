package com.michael.study.base.thread.lock;

/**
 * 类功能描述:
 * <pre>
 *   自定义锁的示范
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/15 10:02
 */
public class LockDemo3 {

    DemoLock3 lock = new DemoLock3();

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

        LockDemo3 lockDemo3 = new LockDemo3();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    lockDemo3.incr();
                }
            }).start();
        }

        Thread.sleep(1000L);

        System.out.println(lockDemo3.i);

    }
}


