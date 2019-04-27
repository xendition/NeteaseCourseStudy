package com.michael.study.performance.base.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 指定 class 进行加载的测试
 * <pre>
 * 1. 类不会重复加载: 同一个类加载器，类名一样，代理是同一个类，不会重复加载(通过不断创新新的类加载器加载class可以实现class的热加载)
 * 2. 类的卸载需要满足以下两个条件：
 *      a. 该Class所有的实例都已经被gc
 *      b. 加载该类的 ClassLoader 实例已经被GC
 *      PS：可以在JVM启动参数中添加 -verbose:class 参数，查看类加载/卸载日志信息
 * 3. 双亲委派模型：类的加载总是从最顶层的类加载器开始尝试加载，加载不成功才调用下一层类加载器加载，确定类只会加载一次
 * </pre>
 *
 * @author Michael
 */
public class ClassLoaderTest {

    private static String testClassUrl = "file:D:\\";
    private static String testClassName = "Hello";

    public static void main(String[] args) throws Exception {


        // URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{
        //         new URL(testClassUrl)
        // });
        // URLClassLoader parentClassLoader = new URLClassLoader(new URL[]{
        //         new URL(testClassUrl)
        // });

        while (true) {

            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL(testClassUrl)});

            Class loadClass = urlClassLoader.loadClass(testClassName);

            System.out.println("Hello 使用的类加载器为 -> " + loadClass.getClassLoader());

            // newInstance 时会调用 static 方法与模块 只调用一次
            Object instance = loadClass.newInstance();

            Object value = loadClass.getMethod("test").invoke(instance);

            System.out.println("value 返回值为 -> " + value);

            Thread.sleep(1200L);

            System.out.println();

            // instance = null;
            //
            // urlClassLoader = null;
            //
            // System.gc();
        }
    }
}
