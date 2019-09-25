package com.arbonkeep.classloader;

public class Test12 {
    public static void main(String[] args) {

        //可以通过下列输出获得各个类加载器的访问文件目录
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }
}
