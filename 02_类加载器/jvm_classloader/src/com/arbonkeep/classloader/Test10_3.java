package com.arbonkeep.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 自定义类加载器，针对Test10_1进行改造，演示示的指定该类加载器的父加载器 等
 */
public class Test10_3 extends ClassLoader{
    private String classLoaderName;//定义成员变量类加载器的名字

    private String path;//表示从哪里开始加载

    private final String fileExtension = ".class";//文件的扩展名为.class

    //构造方法(将系统类加载器当作该类的父加载器)
    public Test10_3(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    //构造方法（显示的指定该类加载器的父加载器）
    public Test10_3(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    //构造方法
    public Test10_3(ClassLoader parent) {
        super(parent);
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

    //定义一个主方法进行测试（演示类的卸载）
    public static void main(String[] args) throws Exception {
        Test10_3 loader1 = new Test10_3("loader1");
        //loader1.setPath("C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\JVM-Study\\02_类加载器\\jvm_classloader\\out\\production\\jvm_classloader\\com\\arbonkeep\\classloader");
        loader1.setPath("C:\\Users\\asus\\Desktop\\");

        Class clazz = loader1.loadClass("com.arbonkeep.classloader.Test1");
        System.out.println("class:" + clazz.hashCode());
        Object obj = clazz.newInstance();
        System.out.println(obj);

        System.out.println();

        System.out.println("-----------------------------");

        clazz = null;
        obj = null;
        loader1 = null;
        System.gc();

        loader1 = new Test10_3("loader1");
        loader1.setPath("C:\\Users\\asus\\Desktop\\");

        clazz = loader1.loadClass("com.arbonkeep.classloader.Test1");
        System.out.println("class:" + clazz.hashCode());
        obj = clazz.newInstance();
        System.out.println(obj);

        System.out.println();

        /*
            -XX:+TraceClassUnloading:查看追踪类的卸载的虚拟机参数

         */
    }


}
