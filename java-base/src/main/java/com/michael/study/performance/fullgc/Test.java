package com.michael.study.performance.fullgc;

import java.util.concurrent.locks.LockSupport;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/06/02 12:36
 */
public class Test {

    public static void main(String[] args) {

        LockSupport.unpark(Thread.currentThread());

        LockSupport.park();

        System.out.println("hello");

        LockSupport.unpark(Thread.currentThread());

        LockSupport.unpark(Thread.currentThread());

        LockSupport.park();

        LockSupport.park();

        System.out.println("hello");

    }
}
