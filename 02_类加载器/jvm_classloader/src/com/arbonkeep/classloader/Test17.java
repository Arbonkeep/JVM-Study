package com.arbonkeep.classloader;

import sun.misc.Launcher;

public class Test17 {

    /*
    在oracle公司提供的Hotspot实现中，系统属性sun.boot.class.path如果修改错了，则运行会出错
    提示信息如下：
        Error occurred during initialization of VM
        java/lang/NoClassDefFoundError: java/lang/Object

    这是由于在目录中没有java.lang.Object这个类

     */
    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));

        System.out.println(ClassLoader.class.getClassLoader());
        //输出null，这是由根类加载器进行加载的

        //系统类加载器和扩展类加载器都是通过根类加载器进行加载的
        System.out.println(Launcher.class.getClassLoader());

        System.out.println("-------------------------");

        System.out.println(System.getProperty("java.system.class.loader"));

        System.out.println(Test17.class.getClassLoader());
        System.out.println(Test10_3.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());

        /*
            在IDEA中，获取的系统类加载器都是AppClassLoader
            但是，如果我们指定系统类加载器后，那么ClassLoader的系统类加载器就是
            我们指定的类加载器
         */

    }
}

/**
    如果直接通过cmd进行加载该类，那么系统类的目录显示为 . ，而通过IDEA则会得到目录(
    并且会自动添加一些目录)

    （通过命令行实现系统类加载器的变更）
    java -Djava.system.class.loader=com.arbonkeep.classloader.Test10_3 com.arbonkeep.classloader.Test17
    前面指定固有属性，后面为执行的类
    如果需要得出正确结果需要在自定义类加载器(Test10_3)中定义一个有classloader为参数的构造方法
   （否则会报出异常）,此时ClassLoader的系统类加载器已经指定为自定义的类加载器
 */