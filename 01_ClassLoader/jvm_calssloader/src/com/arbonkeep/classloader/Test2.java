package com.arbonkeep.classloader;

/**
 * 常量在编译阶段会存入到调用这个常量的方法所在的类的常量池中
 * 本质上调用类并没有直接引用到定义常量的类，因此并不会触发定义常量的类的初始化
 *
 * 注意：这里指的是将常量存放到了Test2的常量池中，之后Test2与MyParent2就没有任何关系了
 *      甚至我们可以将MyParent2中的字节码文件直接删除了
 */

public class Test2 {
    public static void main(String[] args) {
        System.out.println(MyParent2.i);
    }

}

/**
 * 分析：当我们在主方法中调用调用str时，由于str是一个存放在常量池中的常量，那么就不需要通过
 *      MyParent2进行调用，而是直接从常量池中获取，即MyParent没有被初始化也是可以的
 */

class MyParent2 {
    public static final String str = "hello";

    public static final short s = 127;

    public static final int i = 128;

    public static final int x = 1;

    static {
        System.out.println("MyParent2中的静态代码块");
    }
}
