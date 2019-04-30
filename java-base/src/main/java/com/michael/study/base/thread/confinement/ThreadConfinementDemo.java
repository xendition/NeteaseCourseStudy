package com.michael.study.base.thread.confinement;

/**
 * 类功能描述:
 * <pre>
 *      线程封闭示例 - ThreadLocal
 *
 *      栈封闭：局部变量的固有属性之一就是栈封闭
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/04/27 12:48
 */
public class ThreadConfinementDemo {

    /** threadLocal变量，每个线程都有一个副本，互不干扰 */
    public static ThreadLocal<String> value = new ThreadLocal<>();


    public static void main(String[] args) throws Exception {
        new ThreadConfinementDemo().threadLocalTest();
    }

    /**
     * threadlocal测试
     *
     * @throws Exception
     */
    public void threadLocalTest() throws Exception {

        System.out.println("threadlocal线程封闭示例");

        // 主线程设置值
        value.set("这是主线程设置的123");
        String v = value.get();
        System.out.println("线程1执行之前，主线程取到的值：" + v);

        new Thread(() -> {
            String v1 = value.get();
            System.out.println("线程1取到的值：" + v1);
            // 设置 threadLocal
            value.set("这是线程1设置的456");

            v1 = value.get();
            System.out.println("重新设置之后，线程1取到的值：" + v1);
            System.out.println("线程1执行结束");
        }).start();

        // 等待所有线程执行结束
        Thread.sleep(1000L);

        v = value.get();
        System.out.println("线程1执行之后，主线程取到的值：" + v);

    }

}