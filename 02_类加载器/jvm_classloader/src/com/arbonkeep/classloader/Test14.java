package com.arbonkeep.classloader;

import java.lang.reflect.Method;

public class Test14 {
    public static void main(String[] args) throws Exception {
        Test10_3 loader1 = new Test10_3("loader1");

        Test10_3 loader2 = new Test10_3("loader2");

        Class<?> clazz1 = loader1.loadClass("com.arbonkeep.classloader.MyPerson");

        Class<?> clazz2 = loader2.loadClass("com.arbonkeep.classloader.MyPerson");

        System.out.println(clazz1 == clazz2);
         //true，这是因为这两个类加载器都是通过系统类加载器进行加载，而加载一次后，不会第二次加载
         //同一个类，而是直接将第一次加载的结果返回，所以结果为true。 这里不能够理解为两个类加载器
         //加载的是同一个对象而导致结果为true

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        method.invoke(object1,object2);//第一个参数为调用者，第二个参数作为方法的形式参数

    }

}
