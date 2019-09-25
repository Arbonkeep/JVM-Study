package com.arbonkeep.classloader;

public class Test2 {

    public static void main(String[] args) {
        System.out.println(FinalTest.x);

    }
}

class FinalTest {
    public static final int x = 5;

    static  {
        System.out.println("FinalTest 的静态代码块");
    }
}
