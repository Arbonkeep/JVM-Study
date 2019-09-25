package com.arbonkeep.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 自定义类加载器(自定义类加载器必须继承ClassLoader接口)
 */
public class Test10 extends ClassLoader{
    private String classLoaderName;//定义成员变量类加载器的名字

    private final String fileExtension = ".class";//文件的扩展名为.class

    //构造方法(将系统类加载器当作该类的父加载器)
    public Test10(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    //构造方法（显示的指定该类加载器的父加载器）
    public Test10(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    //重写toString
    @Override
    public String toString() {
        return "[" + this.classLoaderName + "]";
    }

    @Override
    protected Class<?> findClass(String className) {
        //输出检测一下是否调用了findClass方法
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

        try {
            //2.由于传入的名字为.连接，所以需要转换成/
            //this.classLoaderName = classLoaderName.replace(".", "\\");

            //3.进行io操作
            is = new FileInputStream(new File(name + this.fileExtension));

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

    public static void test(ClassLoader classLoader) throws Exception {
        Class clazz = classLoader.loadClass("com.arbonkeep.classloader.Test1");
        Object obj = clazz.newInstance();

        System.out.println(obj);
        System.out.println(obj.getClass().getClassLoader());//AppClassLoader
        /*
        通过分析，我们知道加载Test1并不是我们自定义的类加载器加载的，而是通过自定义类的父加载
        器加载的，这是因为在构造方法中使用了应用类加载器作为父加载器（也就是实现了双亲委派机制）
        由此得出，系统类加载器是Test1的定义类加载器，而系统类加载器和Test10是Test1的初始类加
        载器
        */
    }

    //定义一个主方法进行测试
    public static void main(String[] args) throws Exception {
        Test10 loader = new Test10("loader");
        test(loader);

    }


}
