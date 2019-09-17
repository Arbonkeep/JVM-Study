package com.arbonkeep.classloader;

public class Test3 {
    static {
        System.out.println("Test3 的静态代码块");
    }
    public static void main(String[] args) {
        System.out.println(Child.b);
        /*
        Test3 的静态代码块
        Parent 的静态代码块
        Child 的静态代码块
        6
         */

    }
}

class Parent {
    static int a = 1;
    static  {
        System.out.println("Parent 的静态代码块");
    }
}

class Child extends Parent {
    static int b = 6;
    static {
        System.out.println("Child 的静态代码块");
    }
}
