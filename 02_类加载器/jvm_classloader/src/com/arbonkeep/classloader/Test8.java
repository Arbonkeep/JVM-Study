package com.arbonkeep.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class Test8 {
    public static void main(String[] args) throws IOException {
        //获取当前线程的文本加载器
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        String resourcStr = "com/arbonkeep/classloader/Test8.class";

        Enumeration<URL> urls = classLoader.getResources(resourcStr);

        while(urls.hasMoreElements()) {
            System.out.println(urls.nextElement());
        }

        System.out.println("-----------------------");

        Class clazz = Test8.class;
        System.out.println(clazz.getClassLoader());//这是由应用类加载器进行加载的

        Class clazz2 = String.class;
        System.out.println(clazz2.getClassLoader());//这是由启动类加载器加载的，String位于rt.jar下

    }
}
