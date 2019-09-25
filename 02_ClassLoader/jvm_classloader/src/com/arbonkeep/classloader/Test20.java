package com.arbonkeep.classloader;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;
/**
 * 演示JDBC的加载内容
 */
public class Test20 {
    public static void main(String[] args) {
        //改动一下原有的上下文类加载器（ExtClassLoader）
       //Thread.currentThread().setContextClassLoader(Test20.class.getClassLoader().getParent());

        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);

        Iterator<Driver> iterator = loader.iterator();

        while(iterator.hasNext()) {
            Driver driver = iterator.next();

            System.out.println("driver:" + driver.getClass() + ",loader:" + driver.getClass().getClassLoader());

        }

        System.out.println("当前线程的上下文类加载器：" + Thread.currentThread().getContextClassLoader());

        System.out.println("ServiceLoader的类加载器:" + ServiceLoader.class.getClassLoader());

        /*
driver:class com.mysql.jdbc.Driver,loader:sun.misc.Launcher$AppClassLoader@18b4aac2
driver:class com.mysql.fabric.jdbc.FabricMySQLDriver,loader:sun.misc.Launcher$AppClassLoader@18b4aac2
当前线程的上下文类加载器：sun.misc.Launcher$AppClassLoader@18b4aac2
ServiceLoader的类加载器:null                     //这是由于ServiceLoader是java的核心类


         */

        /*
        对改动后的代码分析：
            首先打印的是扩展类加载器，然后是根类加载器，但是值得注意的是，代码中的while循环
            内的代码并没有执行，这是因为我们将上下文类加载器指定为ExtClassLoader，然而我们的
            mySQL的驱动jar包是在classpath目录下的，所以通过修改后的上下文类加载器已经不能够
            加载到mySQL的驱动jar包了，所以是没有输出的

         */
    }
}
