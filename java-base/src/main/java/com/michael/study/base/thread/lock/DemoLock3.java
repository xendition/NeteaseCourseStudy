package com.michael.study.base.thread.lock;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * 类功能描述:
 * <pre>
 *   可以正确达到效果的锁实现
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/15 11:08
 */
public class DemoLock3 implements Lock {

    /**
     * 锁的拥有者（独享锁 -- 资源只能被一个线程占有）
     */
    AtomicReference<Thread> owner = new AtomicReference<>();

    /**
     * 需要锁池
     */
    LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>();

    @Override
    public void lock() {
        // TODO 拿到锁，等待
        waiters.add(Thread.currentThread());
        // CAS -- 此处直接CAS，是一种非公平的实现
        while (!owner.compareAndSet(null, Thread.currentThread())) {
            LockSupport.park(); // 挂起，等待被唤醒...
        }
        // 拿到锁后从等待队列中删除
        try {
            waiters.remove(Thread.currentThread());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unlock() {
        if (owner.compareAndSet(Thread.currentThread(), null)) {

            // 释放锁之后，要唤醒线程(所有 -- 惊群效应)
            // for (Thread waiter : waiters) {
            //     LockSupport.unpark(waiter);
            // }

            // LockSupport.unpark(waiters.peek());

            // 使用poll()无法达到预期 TODO 移除元素会出现什么情况?
            LockSupport.unpark(waiters.poll());

            /**
             * add      增加一个元索|如果队列已满，则抛出一个IIIegaISlabEepeplian异常
             * remove   移除并返回队列头部的元素|如果队列为空，则抛出一个NoSuchElementException异常
             * element  返回队列头部的元素|如果队列为空，则抛出一个NoSuchElementException异常
             * offer    添加一个元素并返回true|如果队列已满，则返回false
             * poll     移除并返问队列头部的元素|如果队列为空，则返回null
             * peek     返回队列头部的元素|如果队列为空，则返回null
             * put      添加一个元素|如果队列满，则阻塞
             * take     移除并返回队列头部的元素
             */
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
