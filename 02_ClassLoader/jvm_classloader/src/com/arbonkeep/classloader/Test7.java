package com.arbonkeep.classloader;

public class Test7 {
    public static void main(String[] args) {
        //获取系统类加载器
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);

        while(null != classLoader ) {//这里用null作为条件，是因为在oracal的jdk中用null作为启动类加载器
             classLoader = classLoader.getParent();
            System.out.println(classLoader);//获取父类加载器
        }

        /*

         sun.misc.Launcher$AppClassLoader@18b4aac2          应用类加载器
         sun.misc.Launcher$ExtClassLoader@1540e19d          扩展类加载器
         null                                               启动类加载器

         */
    }

}
