package com.michael.study.performance.base.classloader;


/**
 * 查看类加载器实例
 *
 * @author Michael
 */
public class ClassLoaderView {

    public static void main(String[] args) throws ClassNotFoundException {

        ClassLoader currentClassLoader = ClassLoaderView.class.getClassLoader();

        System.out.println("核心库加载器：BootStrap ClassLoader(由C/C++实现) 加载 JRE_HOME/jre/lib 目录、JDK核心类库(如rt.jar)、用户配置目录 \n-> " +
                           String.class.getClassLoader());

        System.out.println("扩展类库加载器：Extension ClassLoader 加载 JRE_HOME/jre/lib/ext 目录、JDK拓展包、用户配置目录 \n-> " +
                           currentClassLoader.loadClass("com.sun.nio.zipfs.ZipCoder").getClassLoader());

        System.out.println("应用程序加载器：AppClassLoader 加载 java.class.path 指定的目录、用户应用程序 class-path 或者 java命令运行时参数 -cp \n-> " + currentClassLoader);

        System.out.println("应用程序加载器的父类 \n-> " + currentClassLoader.getParent());

        System.out.println("应用程序加载器的父类的父类 \n-> " + currentClassLoader.getParent().getParent());
    }
}
