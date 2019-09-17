package com.arbonkeep.classloader;

/**
 * ClassLoader的原码分析之数组演示（在第一部分的Test4中有说明）
 *
 * 数组类的 Class 对象不是由类加载器创建的，而是由 Java 运行时根据需要自动创建。
 * 数组类的类加载器由 Class.getClassLoader() 返回，该加载器与其元素类型的类
 * 加载器是相同的；如果该元素类型是基本类型，则该数组类没有类加载器
 *
 */
public class Test9 {
    public static void main(String[] args) {
        String[] arr = new String[2];
        System.out.println(arr.getClass().getClassLoader());

        /*
          根据上述结论，我们可以知道，String的类加载器为启动类加载器，在这表示就是null,
          由于数组类的类加载器与其元素类型(String)的类加载器是一样的，那么，我们可以断定
          arr的类加载器应该为null
         */

        System.out.println("--------------------------");

       Test9[] arr2 = new Test9[1];
        System.out.println(arr2.getClass().getClassLoader());

        /*
        同理，由于Test9的类加载器为应用型类加载器，那么arr2的类加载器也是应用类加载器

         */

        System.out.println("---------------------------");

        int[] arr3 = new int[3];

        System.out.println(arr3.getClass().getClassLoader());

        /*
        根据上述结论，我们知道数组的类型为基本数据类型，那么就可以断定该数组没有类加载器，
        但是会输出null，注意这个null与arr的null含义不同，arr中null表示启动类加载器，而
        这个null表示没有ClassLoader
         */

    }
}
