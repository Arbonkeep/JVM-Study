package com.arbonkeep.classloader;

import java.lang.reflect.Method;

/**
 * 针对Test14的改造，我们指定了类加载器的访问路径
 */
public class Test15 {
    public static void main(String[] args) throws Exception {
        Test10_3 loader1 = new Test10_3("loader1");

        Test10_3 loader2 = new Test10_3("loader2");

        loader1.setPath("C:\\Users\\asus\\Desktop\\");
        loader2.setPath("C:\\Users\\asus\\Desktop\\");

        Class<?> clazz1 = loader1.loadClass("com.arbonkeep.classloader.MyPerson");

        Class<?> clazz2 = loader2.loadClass("com.arbonkeep.classloader.MyPerson");

        System.out.println(clazz1 == clazz2);

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        method.invoke(object1,object2);

        /*
        分析：
            在我们没有删除classpath中的MyPerson.class时，都是系统类加载器进行加载的，clazz1==
            clazz2 的结果为true，后面正常运行

            当我们删除classpath中的MyPerson.class时，两者都是通过自定类加载器进行加载的，（分别
            为loader1，和loader2），由于两者的命名空间不同，所以clazz1 == clazz2的结果为false
            此时，后面代码会报出异常(ClassCastException)，根据上面代码可以知道两个类加载器的命名
            空间不同，当我们通过反射创建实例时，就是利用一个命名空间的对象调用方法，而另一个命名空间
            的对象作为参数，所以会报出这个异常

            异常信息：
            com.arbonkeep.classloader.MyPerson cannot be cast to com.arbonkeep.classloader.MyPerson
         */

        /*
         对于为什么要在MyPerson类中直接传入一个object对象，然后进行强转的分析

                这是因为如果在MyPerson中的setMyPerson方法直接传入MyPerson对象，那么就会反射阶段的
                getMethod方法中需要传入MyPerson.class，而我们在classpath中删除了MyPerson.class
                这两者相互冲突，这么做是为了解决这一冲突

         */


    }

}
