package com.arbonkeep.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 自定义类加载器，针对Test10_1进行改造，演示示的指定该类加载器的父加载器 等
 */
public class Test10_2 extends ClassLoader{
    private String classLoaderName;//定义成员变量类加载器的名字

    private String path;//表示从哪里开始加载

    private final String fileExtension = ".class";//文件的扩展名为.class

    //构造方法(将系统类加载器当作该类的父加载器)
    public Test10_2(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    //构造方法（显示的指定该类加载器的父加载器）
    public Test10_2(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //重写toString
    @Override
    public String toString() {
        return "[" + this.classLoaderName + "]";
    }

    @Override
    protected Class<?> findClass(String className) {
        //输出检测一下是否调用了findClass方法（用于测试是否通过自定义类加载器加载的）
        System.out.println(className);
        System.out.println("类加载器为：" + this.classLoaderName);

        byte[] data = this.loadClassData(className);
        return this.defineClass(className, data, 0, data.length);//将获取的数据转换为字节码文件
    }

    //声明获取字节数组数据的loadClassData方法
    public byte[] loadClassData(String name) {
        //1.声明需要用到的变量
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        //2.由于传入的名字为.连接，所以需要转换成/
        name = name.replace(".", "\\");

        try {

            //3.进行io操作
            is = new FileInputStream(new File(this.path + name + this.fileExtension));

            baos = new ByteArrayOutputStream();
            int b = 0;
            while((b = is.read()) != -1 ) {
                baos.write(b);
            }

            data = baos.toByteArray();//转换为字节数组

        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                baos.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    //定义一个主方法进行测试
    public static void main(String[] args) throws Exception {
        Test10_2 loader1 = new Test10_2("loader1");
        //loader1.setPath("C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\JVM-Study\\02_类加载器\\jvm_classloader\\out\\production\\jvm_classloader\\com\\arbonkeep\\classloader");
        loader1.setPath("C:\\Users\\asus\\Desktop\\");

        Class clazz = loader1.loadClass("com.arbonkeep.classloader.Test1");
        System.out.println("class:" + clazz.hashCode());
        Object obj = clazz.newInstance();
        System.out.println(obj);

        System.out.println();

        System.out.println("--------------------------");

        //将loader1指定为loader2的父加载器
        Test10_2 loader2 = new Test10_2(loader1, "loader2");
        //这里loade1与loader2同样为Test10_2的实例，但是loader1可以成为loder2的父加载器，
        //这是因为在双亲委托机制中类加载器之间是一种包含关系


        loader2.setPath("C:\\Users\\asus\\Desktop\\");

        Class<?> clazz2 = loader2.loadClass("com.arbonkeep.classloader.Test1");
        System.out.println("class:" + clazz2.hashCode());

        Object obj2 = clazz2.newInstance();
        System.out.println(obj2);

        System.out.println();

        System.out.println("---------------------");

        /*
            输出：
            com.arbonkeep.classloader.Test1
            类加载器为：loader1
            class:21685669
            com.arbonkeep.classloader.Test1@7f31245a

            --------------------------
            class:21685669
            com.arbonkeep.classloader.Test1@6d6f6e28

            分析：当将loader1作为loader2的父加载器并将Test1.class删除后，会得到上面结果，
                 首先系统类加载器尝试加载Test1，但是Test1.class被删除，所以加载失败，那么
                 就会和使用自定义加载器加载，因此就会得到上半部分结果。由于loader2的父加载器
                 为loader1，而loader1已经加载了Test1，所以不会再次加载，而是将上次加载的结果
                 返回，即得到下半部分结果（这里可以参考loadClass方法的执行顺序，参考markdown
                 笔记部分）

         */

//        Test10_2 loader3 = new Test10_2("loader3");
//
//        loader3.setPath("C:\\Users\\asus\\Desktop\\");
//        Class<?> clazz3 = loader3.loadClass("com.arbonkeep.classloader.Test1");
//        System.out.println("class:" + clazz3.hashCode());
//
//        Object obj3 = clazz3.newInstance();
//        System.out.println(obj3);

        /*
            分析：在没有删除Test1.class对象的情况下，第一次加载是通过系统类加载器加载，第二次输出结果也是
                 与第一次一样，因为加载过了，所以直接将加载的结果返回。第三次的父加载器就是系统类加载器，
                 由于系统类加载器已经加载过，所以直接返回，第三次结果也与前两次一样


                 输出：
                    com.arbonkeep.classloader.Test1
                    类加载器为：loader1
                    class:21685669
                    com.arbonkeep.classloader.Test1@7f31245a

                    --------------------------
                    class:21685669
                    com.arbonkeep.classloader.Test1@6d6f6e28

                    ---------------------
                    com.arbonkeep.classloader.Test1
                    类加载器为：loader3
                    class:856419764
                    com.arbonkeep.classloader.Test1@2503dbd3

                 在删除了Test1.class的情况下，第一次加载是通过自定义类加载器加载，第二次由于之前loader1
                 已经加载过，所以直接返回加载的结果，而第三次则是因为与前两次不在同一个生命空间，所以第三
                 次通过自定义类加载器重新加载了

         */

        //将loader2作为loader3的父加载器
        Test10_2 loader3 = new Test10_2(loader2,"loader3");

        loader3.setPath("C:\\Users\\asus\\Desktop\\");
        Class<?> clazz3 = loader3.loadClass("com.arbonkeep.classloader.Test1");
        System.out.println("class:" + clazz3.hashCode());

        Object obj3 = clazz3.newInstance();
        System.out.println(obj3);


        /*
            分析：在没有删除Test1.class的情况下，第一次是通过系统类加载器进行加载的，第二次和第三次
                 都是由于之前系统类加载器钱已经加载过，所以直接返回结果

                 在删除Test2.class的情况下，第一次是通过自定义类加载器进行加载的，而第二次和第三次
                 由于上一个加载器作为该加载器的加载器，所以都是直接返回loader1加载的结果，不再进行
                 加载（本质上是同一个对象）
         */



    }


}
