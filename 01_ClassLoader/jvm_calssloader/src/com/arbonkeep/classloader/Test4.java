package com.arbonkeep.classloader;

/**
 * 对于数组实例来说，其类型是由JVM在运行期动态生成的，表示为[Lcom.arbonkeep.classloader.MyParent4
 * 这种形式。动态生成的类型，其父类型就是Object
 *
 * 对于数组来说，JavaDoc经常将构成数组的元素为Component，实际上就是将数组降低一个维度后的类型
 */

public class Test4 {
    public static void main(String[] args) {
        MyParent4 myParent4 = new MyParent4();
        //这是会初始化MyParent4的，属于主动调用的第一种情况
        MyParent4 myParent5 = new MyParent4();
        //这是不会再次进行初始化的，只有在定义次调用时才会进行初始化

        System.out.println(myParent4.getClass());//class com.arbonkeep.classloader.MyParent4
        System.out.println("----------------------");
        System.out.println(myParent5.getClass());//class com.arbonkeep.classloader.MyParent4

        MyParent4[] mp4 = new MyParent4[1];
        System.out.println(mp4.getClass());//[Lcom.arbonkeep.classloader.MyParent4

        MyParent4[][] mp4s = new MyParent4[1][1];
        System.out.println(mp4s.getClass());//[[Lcom.arbonkeep.classloader.MyParent4

        System.out.println(mp4.getClass().getSuperclass());
        System.out.println(mp4s.getClass().getSuperclass());

        System.out.println("-----------------------");

        int[] ints = new int[1];
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getSuperclass());

        byte[] bytes = new byte[1];
        System.out.println(bytes.getClass());
        System.out.println(bytes.getClass().getSuperclass());

        short[] shorts = new short[1];
        System.out.println(shorts.getClass());
        System.out.println(shorts.getClass().getSuperclass());

        char[] chars = new char[1];
        System.out.println(chars.getClass());
        System.out.println(chars.getClass().getSuperclass());

        boolean[] booleans = new boolean[1];
        System.out.println(booleans.getClass());
        System.out.println(booleans.getClass().getSuperclass());

    }

}

class MyParent4 {
    static {
        System.out.println("MyParent4 的静态代码块");
    }
}
