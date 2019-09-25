package com.arbonkeep.classloader;

public class Test18 {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
        //AppClassLoader
        System.out.println(Thread.class.getClassLoader());
        //null（根类加载器）
    }
}
/*
    在双亲委托模型下，类加载是由下至上的，即下层的类加载器会委托上层的你类加载器进加载。但是对于SPI来说，有些
    接口时Java核心库所提供的，而Java核心库是由启动类加载器进行加载的，而这些接口的实现却来自不同的jar包（由
    厂商提供），Java的启动类加载器是不会加载其他来源的jar包，这样传统的双亲委托模型无法满足SPI的需求，而通过
    给当前线程设置上下文类加载器，就可以由设置的上下文类加载器来实现对于接口实现类的加载

        对于上述结论的举例：（JDBC的描述）
            首先，SQL是java的核心类库提供的，那么如果需要加载里面实现接口的类，就需要通过启动类加载器进行
            加载，然而对于JDBC的具体实现是由数据库厂商提供的实现类来实现的，当我们使用的时候就需要将相关的
            jar包导入到classpath中，根据之前所学知识，那么我们知道classpath中class文件的加载是通过系统
            类加载器进行加载的，所以启动类加载器就不能够加载到classpath中相关的jar包，而通过上下文加载器
            进行加载就能够解决这一问题

 */
