package com.arbonkeep.classloader;

/**
 * 声明测试类，用于测试类加载的复杂加载
 */
public class Test11 {
    public static void main(String[] args) throws Exception {
        Test10_3 loader1 = new Test10_3("loader1");

        Class<?> clazz = loader1.loadClass("com.arbonkeep.classloader.MySample");

        System.out.println("class: " + clazz.hashCode());

        //如果将下列代码注释，那么并不会实例化MySample对象，即MySample对象不会被调用
        //因此也就不会实例化MyCat，即没有进行对MyCat对象的主动使用，也就不会加载MyCat.class
        Object object = clazz.newInstance();//通过反射获取对象
        //由此可见，加载MySample与MyCat的加载器都是应用类加载器


    }
}
