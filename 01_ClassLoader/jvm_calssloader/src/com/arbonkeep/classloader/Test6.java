package com.arbonkeep.classloader;

/**
 * 分析：
 *      情况一：当静态变量counter2在前面时，那么在准备阶段首先会为两个变量声明默认值0，
 *              然后初始化调用构造进行counter1和counter2加1的操作，所以最后输出的结果
 *              都为1
 *
 *      情况二：当静态变量counter2在后面时，同样在准备阶段会为两个变量声明默认值0，然后
 *              初始化调用构造进行counter1和counter2加1的操作，此时（在构造方法中）打
 *              印两个变量的值都为1，但是在下面有重新为counter2进行赋值为0，所以将之前
 *              的值覆盖了，所以最后在主方法中输出counter1 = 1，counter2 = 0
 *
 *      情况三：在情况二的基础上，counter1最初值为1，那么此时，首先依然会在准备阶段为两
 *              个变量声明默认值0，但是在初始化化时，从上自下的顺序，首先会将counter1
 *              的值赋值为1，然后调用构造方法counter1与counter2进行加1，那么此时打印
 *              counter1=2，counter2=1，由于后面重新为counter2进行赋值为0，所以最终
 *              的结果为counter1 = 2，counter2 = 0
 *
 */

public class Test6 {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println("counter1:" + Singleton.counter1);
        System.out.println("counter2:" + Singleton.counter2);
    }

}

class Singleton {
    public static int counter1;//情况三：counter1 = 1；

    //public static int counter2 = 0;//这种情况counter1与counter2的值都是1

    private static Singleton singleton = new Singleton();

    private Singleton() {
        counter1++;
        counter2++;//准备阶段的重要意义

        System.out.println(counter1);//1,在准备阶段之后，初始化时值为1
        System.out.println(counter2);//1,在准备阶段之后，初始化时值为1
    }

    public static int counter2 = 0;//这种情况counter2的值为0

    public static Singleton getInstance() {
        return singleton;
    }
}
