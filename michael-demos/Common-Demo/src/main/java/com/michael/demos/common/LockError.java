package com.michael.demos.common;

/**
 * 类功能描述:
 * <pre>
 *   xxxx
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2020/5/11 17:07
 */
public class LockError {

    public static void main(String[] args) {
        Object lock = new Object();
        int i = 0;
        while (true) {
            synchronized (lock) {
                System.out.println(i++);
                lock = null;
            }
        }
    }
}
