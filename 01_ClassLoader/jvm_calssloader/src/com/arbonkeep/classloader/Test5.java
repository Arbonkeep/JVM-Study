package com.arbonkeep.classloader;

import java.util.Random;

/**
 * 当一个接口在初始化时，并不要求其父接口都完成了初始化，只有真正使用到父接口的时候（如：引用接口中所定义的常
 * 量时）才会被初始化
 *
 * 也就是说在删除字节码文件的情况下依然可以获取到接口中变量的值(下列代码并不能证明这个结论)
 *
 *
 */

public class Test5 {
    public static void main(String[] args) {
        //System.out.println(MyChild5.b);

        System.out.println(MyParent5_1.thread);
    }
}

/**
 * 这里演示的是常量问题
 */
/*interface MyParent5 {
    public static final int a = new Random().nextInt(2);//在接口中变量默认被final修饰
}*/

//结论：如果我们在接口中声明了一个常量，并且常量值在编译期就能够确定，那么就不会加载其父接口,而是在Test5
//      的常量池中。

/*
    如果将接口改为class，那么就必须加载该类，这是因为在类中，定义的b并不是一个常量
 */
/*interface MyChild5 extends MyParent5 {
    public static int b = 5;
}*/


/**
 * 演示：当一个接口在初始化时，并不要求其父接口都完成了初始化，只有真正使用到父接口的时候才会被初始化
 *
 */
interface MyGrandPa {
    public static Thread thread = new Thread() {
        {
            System.out.println("MyGrandPa 被初始化了");
        }
    };
}

interface MyParent5 {
    public static Thread thread = new Thread() {
        {
            System.out.println("MyParent5 被初始化了");
        }
    };

    /**
     * 如果MyParent5被初始化了那么thread就一定被赋值了
     */
}
//如果将父类改成类，那么就会进行初始化也就会输出：MyParent5 被初始化了，满足主动使用
class MyChild5 implements MyParent5 {
    public static int b = 5;
 }

/**
 * 演示：在初始化一个接口的时候，并不会先初始化它的父接口
 */

interface MyGrandPa_1 {
    public static Thread thread = new Thread() {
        {
            System.out.println("MyGrandPa_1 被初始化了");
        }
    };
}

interface MyParent5_1 extends MyGrandPa_1{
    public static Thread thread = new Thread() {
        {
            System.out.println("MyParent5_1 被初始化了");
        }
    };
}
