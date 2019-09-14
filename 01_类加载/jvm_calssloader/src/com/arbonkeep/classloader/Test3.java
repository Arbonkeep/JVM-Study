package com.arbonkeep.classloader;

import java.util.UUID;

/**
 * 当一个常量的值并非编译期可以确定，那么其值就不会放到调用类的常量池中，
 * 这时在程序运行时，会导致主动使用这个常量所在的类，所以会导致这个类被
 * 初始化
 */

public class Test3 {
    public static void main(String[] args) {
        System.out.println(MyParent3.str);
        //会访问静态代码块
    }
}

class MyParent3 {
    public static final String str = UUID.randomUUID().toString();//定义一个常量随机生成UUID值
    //这是一个在编译期不能确定的常量值，需要在类初始化后才能够调用
    static {
        System.out.println("MyParent3 的静态代码块");
    }
}
