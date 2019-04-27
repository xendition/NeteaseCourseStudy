package com.michael.study.base.principle.run;

/**
 * JAVA 运行原理分析 DEMO
 *
 * @author Michael
 */
public class Demo {

    public static void main(String[] args) {

        int x = 500;
        int y = 100;

        int a = x / y;
        int b = 50;

        System.out.println(sum(a,b));

        new Demo().test();
    }

    public static int sum(int a, int b) {
        return a + b;
    }

    public void test() {
        double d = 11;
        System.out.println(d);
    }
}
