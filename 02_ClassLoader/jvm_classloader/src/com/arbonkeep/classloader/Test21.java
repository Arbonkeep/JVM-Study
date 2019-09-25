package com.arbonkeep.classloader;

import java.sql.Connection;
import java.sql.DriverManager;

public class Test21 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");//加载驱动
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/db1",
                "username","password");
    }

    /*
    对上述代码的源码分析：详细参考源代码forName()、getConnection()
        forName():
            Reflection.getCallerClass():会调用加载调用类(Test21的类加载器)
            forName0(className, true, ClassLoader.getClassLoader(caller), caller):
                由源代码可知，ininialize为true，那么说明会进行初始化

        getConnection():



     */
}