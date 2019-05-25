package com.michael.study.base.thread.lock;

/**
 * 类功能描述:
 * <pre>
 *   原子性 DEMO
 *
 *   PS：原子性的本质是对数据一致性的要求
 *
 * </pre>
 *
 * @author Michael
 * @version 1.0
 * @date 2019/05/10 23:32
 */
public class LockDemo1 {

    int i = 0;

    public void incr() {
        // i++; 下文三行代码为实际执行过程
        int x = i;
        int a = x + 1;
        i = a;

        // 采用CAS机制处理，如果用JAVA处理又会产生新的问题 (当处理完 x==i之后 i的值又变化了)
        // CAS只能使用更底层的实现(硬件实现)
        // if (x == i) {
        //     i = a;
        // }
    }

    public static void main(String[] args) throws InterruptedException {

        LockDemo1 lockDemo1 = new LockDemo1();

        for (int i = 0; i < 2; i++) {
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
