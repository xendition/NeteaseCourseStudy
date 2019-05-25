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
 *   这个锁不能达到效果。因为在并发过程中会出现线程A解锁并唤醒等待线程池中的线程时，等待线程池中无线程，未唤醒任何线程，之后B被加入等待队列进入park。
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/15 11:11
 */
public class DemoLock2 implements Lock {
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
        // CAS -- 此处直接CAS，是一种非公平的实现
        while (!owner.compareAndSet(null, Thread.currentThread())) {
            // TODO 没拿到锁，等待
            waiters.add(Thread.currentThread());
            LockSupport.park(); // 挂起，等待被唤醒...
        }
    }

    @Override
    public void unlock() {
        if (owner.compareAndSet(Thread.currentThread(), null)) {
            // 释放锁之后，要唤醒一个线程
            Thread next = waiters.poll();
            LockSupport.unpark(next);
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
