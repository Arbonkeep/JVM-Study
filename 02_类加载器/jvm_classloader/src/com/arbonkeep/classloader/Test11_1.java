package com.arbonkeep.classloader;

/**
 * 声明测试类，用于测试类加载的复杂加载,并对Test11改造，
 *      第一次改造，将类加载器的加载路径指定
 *      第二次改造，在第一次的前提上，我们只删除classpath中MyCat.class
 *      第三次改造，在第一次的前提上，我们只删除classpath中的MySample.class
 *      第四次改造，在MyCat中打印一下MySample.class
 *      第五次改造，在之前改造上，在MySample中打印一下MyCat.class
 *
 */
public class Test11_1 {
    public static void main(String[] args) throws Exception {
        Test10_3 loader1 = new Test10_3("loader1");

        loader1.setPath("C:\\Users\\asus\\Desktop\\");//第一次改造，将类加载器的加载路径指定

        Class<?> clazz = loader1.loadClass("com.arbonkeep.classloader.MySample");

        System.out.println("class: " + clazz.hashCode());

        //如果将下列代码注释，那么并不会实例化MySample对象，即MySample对象不会被调用
        //因此也就不会实例化MyCat，即没有进行对MyCat对象的主动使用，也就不会加载MyCat.class
        Object object = clazz.newInstance();//通过反射获取对象
        //由此可见，加载MySample与MyCat的加载器都是应用类加载器


        /*
         针对第一次改造的分析：
                在没有删除classpath中的MySample.class与MyCat.class时，都是通过系统类加载器进行加载


                在删除了classpath中的MySample.class与MyCat.class时，输出如下：

                com.arbonkeep.classloader.MySample
                类加载器为：loader1
                class: 2133927002
                MySample is loaded by :[loader1]
                com.arbonkeep.classloader.MyCat
                类加载器为：loader1
                MyCat is loaded by : [loader1]

                分析：首先通过自定义类加载器加载，那么前面两行会输出，然后在主方法中打印了class，
                     输出第三行，通过反射获取对象，加载MySample，输出第四行，然后在该构造方法中
                     new了一个MyCat对象，会输出第五行和第六行（这是在自定义类加载器打印的），
                     最后在MyCat构造中打印了最后一行


        针对第二次改造的分析：
             由于在ClassPath中删除了MyCat.class，会导致MySample正常加载，而在加载MyCat时，并不会
             去指定的路径中寻找class文件，而是会直接通过加载MySample的加载器（系统类加载器）进行加载
             但是在classpath中已经没有了MyCat.class,所以会报出一个ClassNotFoundException


        针对第三次改造的分析：
             由于在ClassPath中删除了MySample.class，所以在加载MySample时，会通过自定义类加载器
             loader1进行加载，而同样的在加载MyCat时，会通过加载MySample的加载器进行加载（自定义类
             加载器），但是，自定义类加载器会通过双亲委托机制委托系统类加载器加载（classpath中有
             MyCat.class），所以加载Mycat是通过系统类加载器进行加载的

        针对第四次改造的分析：
            在没有删除classpath中MySample.class与MyCat.class时，输出

                class: 1735600054
                MySample is loaded by :sun.misc.Launcher$AppClassLoader@18b4aac2
                MyCat is loaded by : sun.misc.Launcher$AppClassLoader@18b4aac2
                from myCat: class com.arbonkeep.classloader.MySample

            在删除classpath中MySample.class时，会报出ClassNotFoundException，这是因为在加载时，
            MySample是自定义类加载器加载的，而MyCat是通过应用类加载器加载的，后者是前者的父加载器，
            所以在系统类加载器（父加载器）中所加载的类（MyCat）中看不到自定义加载器（子加载器）中所
            加载的类（MySample）

        针对第五次改造的分析
            在没有删除classpath中MySample.class与MyCat.class时，正常输出

                class: 1735600054
                MySample is loaded by :sun.misc.Launcher$AppClassLoader@18b4aac2
                MyCat is loaded by : sun.misc.Launcher$AppClassLoader@18b4aac2
                from myCat: class com.arbonkeep.classloader.MySample
                from MySample : class com.arbonkeep.classloader.MyCat

            在删除classpath中MySample.class时（这里需要是将MyCat中打印MySample.class释才能看
            到效果，这是因为在没有MySample.class时，这里会报错）,正常输出

                com.arbonkeep.classloader.MySample
                类加载器为：loader1
                class: 2133927002
                MySample is loaded by :[loader1]
                MyCat is loaded by : sun.misc.Launcher$AppClassLoader@18b4aac2
                from MySample : class com.arbonkeep.classloader.MyCat

            分析：首先由于classpath中没有MySample.class，自定义类加载加载MySample，然后
                 应用类加载器加载MyCat，然后能正确访问MyCat.class，这是因为子类加载器加载
                 的类中能正确访问到父类加载器加载的类



         */



    }
}
