package com.arbonkeep.classloader;

public class Test12_1 {
    public static void main(String[]args) throws Exception {
        Test10_3 loader1 = new Test10_3("loader1");

        loader1.setPath("C:\\Users\\asus\\Desktop\\");

        Class<?> clazz = loader1.loadClass("com.arbonkeep.classloader.Test1");

        System.out.println("class:" + clazz.hashCode());

        System.out.println("class loader : " + clazz.getClassLoader());
    }
    /*
    由于我们在指定的根类加载器中存储了Test1.class文件，那么通过双亲委托机制我们可以直到Test1.class
    是通过根类加载器进行加载的
     */


}
