package com.arbonkeep.classloader;

import javafx.scene.Parent;

/**
 * 结论：静态变量定义在哪个类就是对哪个类的主动使用（如果用子类去访问父类的静态变量或方法
 *      都表示是对父类的主动使用）
 *
 * 分析：
 *      首先在调用变量a时，首先会加载Parent3的静态代码块（这是对Parent的主动使用）
 *      然后打印出a的结果，最后在调用Parent3的静态方法，输出结果
 *
 */
public class Test5 {

    public static void main(String[] args) {
        System.out.println(Child3.a);//这里虽然是使用子类调用但是这里是对父类的主动使用

        System.out.println("-------------------");

        Parent3.show();
    }
}

class Parent3 {
    static int a = 4;

    static {
        System.out.println("Parent3的静态代码块");
    }

    static void show() {
        System.out.println("show");
    }
}

class Child3 extends Parent3 {
    static {
        System.out.println("Child3的静态代码块");
    }
}
