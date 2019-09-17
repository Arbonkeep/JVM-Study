package com.arbonkeep.classloader;

/**
 * 首先会初始化Test4的静态代码块进行初始化，然后输出分割线，在new对象时，会先初始化
 * Parent2的静态代码块，再输出分割线，然后在调用a时（由于之前加载了Parent2静态代码块）
 * 所以直接输出a的结果，然后再次输出分割线，最后再调用Child2.b时（按道理，会先加载父类
 * 的静态代码块，但还是之前已经加载了，所以就会直接加载Child2的静态代码块）最后输出b的
 * 结果
 */

public class Test4 {
    static {
        System.out.println("Test4 的静态代码块");
    }

    public static void main(String[] args) {
        Parent2 parent2;

        System.out.println("---------------------");

        parent2 = new Parent2();

        System.out.println("---------------------");

        System.out.println(Parent2.a);

        System.out.println("---------------------");

        System.out.println(Child2.b);
    }
}

class Parent2 {
    static int a = 4;

    static {
        System.out.println("Parent2 的静态代码块");
    }
}

class Child2 extends Parent2{
    static int b = 5;

    static {
        System.out.println("Child2 的静态代码块");
    }
}
