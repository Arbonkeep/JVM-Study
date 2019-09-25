package com.arbonkeep.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 自定义类加载器(自定义类加载器必须继承ClassLoader接口),并对加载器进行完善
 * （之前的类加载器在加载类时不能使用到自定义的加载器）
 */
public class Test10_1 extends ClassLoader{
    private String classLoaderName;//定义成员变量类加载器的名字

    private String path;//表示从哪里开始加载

    private final String fileExtension = ".class";//文件的扩展名为.class

    //构造方法(将系统类加载器当作该类的父加载器)
    public Test10_1(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    //构造方法（显示的指定该类加载器的父加载器）
    public Test10_1(ClassLoader parent, String classLoaderName) {
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
        Test10_1 loader1 = new Test10_1("loader1");
        //loader1.setPath("C:\\Users\\asus\\Desktop\\Computer_Learning_Notes\\JVM-Study\\02_类加载器\\jvm_classloader\\out\\production\\jvm_classloader\\com\\arbonkeep\\classloader");
        loader1.setPath("C:\\Users\\asus\\Desktop\\");

        Class clazz = loader1.loadClass("com.arbonkeep.classloader.Test1");
        System.out.println("class:" + clazz.hashCode());
        Object obj = clazz.newInstance();
        System.out.println(obj);

        System.out.println();

        /*
        分析：利用系统类加载器加载的，这是由于我们指定的路径为classPath,然后在桌面上创建一个文件夹，
             将路径指定为桌面路径当我们将路径指定为C:\Users\asus\Desktop\同时将classpath中的
             Test1.class删除时，就会使用我们自定义的类加载器进行加载，这是由于系统类加载器已经不能
             够完成加载，只能使用自定义类加载
         */

        System.out.println("--------------------------");

        Test10_1 loader2 = new Test10_1("loader2");
        loader2.setPath("C:\\Users\\asus\\Desktop\\");

        Class<?> clazz2 = loader2.loadClass("com.arbonkeep.classloader.Test1");
        System.out.println("class:" + clazz2.hashCode());

        Object obj2 = clazz2.newInstance();
        System.out.println(obj2);

        System.out.println();

        /*
            输出：
            class:356573597
            com.arbonkeep.classloader.Test1@677327b6

            --------------------------
            class:356573597
            com.arbonkeep.classloader.Test1@14ae5a5

            分析：当classPath中有Test1.class时，那么加载时，使用系统类加载器，前面输出的内容是第一次
                 加载形成的，后面输出的内容与前面一致，第二次是不会加载的，只是沿用了第一次加载的内容，
                 这也就是hashCode值一样的原因

            输出：
            com.arbonkeep.classloader.Test1
            类加载器为：loader1
            class:21685669
            com.arbonkeep.classloader.Test1@7f31245a

            --------------------------
            com.arbonkeep.classloader.Test1
            类加载器为：loader2
            class:1173230247
            com.arbonkeep.classloader.Test1@330bedb4

            分析：当classPath中没有Test1.class时，那么加载时，使用自定义类加载器，观察结果发现，两次
                 加载的hashCode值不一样，这说明这两个对象不是同一个对象（值的思考的是同一个类加载了两
                 次，这矛盾吗？这涉及到命名空间（参考markdown笔记））


         */
    }


}
