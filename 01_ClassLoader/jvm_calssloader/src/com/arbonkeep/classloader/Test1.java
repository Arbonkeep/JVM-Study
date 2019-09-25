package com.arbonkeep.classloader;

/**
 * 声明一个测试类，用于演示类加载与初始化（主动使用与被动使用）
 *
 *          结论：对于静态字段来说，只有直接定义了该字段的类才会被初始化
 *               当一个类在初始化时，要求其父类全部都已经初始化完毕了
 *
 *          虚拟机参数：
 *               -XX:+TraceClassLoading，用于追踪类的加载信息并打印出来
 *          三种形式
 *               -xx:+<option>,开启option选项
 *               -xx:-<option>,关闭option选项
 *               -XX:<option>=<value>，表示将option选项设置为value
 */
public class Test1 {
    public static void main(String[] args) {
        System.out.println(child.str);
//          输出：
//          父类的静态代码块
//          Hello



        //System.out.println(child.str2);
//         输出：
//        父类的静态代码块
//        子类的静态代码块
//        World
    }

}

//声明一个父类
class Parent {
    public static String str = "Hello";

    static {
        System.out.println("父类的静态代码块");
    }
}

//声明一个子类继承了Parent

class child extends Parent{
    public static String str2 = "World";

    static {
        System.out.println("子类的静态代码块");
    }
}

