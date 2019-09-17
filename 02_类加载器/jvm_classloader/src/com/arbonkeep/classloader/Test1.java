package com.arbonkeep.classloader;

public class Test1 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.lang.String");

        System.out.println(clazz.getClassLoader());//null
        /*
        这是因为String类是由BootStrap（启动类加载器）加载的
         */

        Class<?> clazz2 = Class.forName("com.arbonkeep.classloader.c");
        System.out.println(clazz2.getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2
        /*
         这是因为c这个类是由AppClassLoader（应用类加载器）加载的
         */
    }
}

class c {}
