package com.arbonkeep.classloader;

/**
 * 结论：通过反射，是主动使用，会导致类的初始化，调用ClassLoader类的loadClass方法加载一个类，
 *      并不是对类的主动使用，不会导致类的初始化
 */
public class Test6 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();//获取一个系统类加载器

        Class<?> clazz = classLoader.loadClass("com.arbonkeep.classloader.CL");//获取字节码文件

        System.out.println(clazz);

        System.out.println("--------------------------");

        //通过反射获取字节码文件
        Class<?> clazz2 = Class.forName("com.arbonkeep.classloader.CL");

        System.out.println(clazz2);

    }
}

class CL {
    static {
        System.out.println("CL的静态代码块");
    }
}
