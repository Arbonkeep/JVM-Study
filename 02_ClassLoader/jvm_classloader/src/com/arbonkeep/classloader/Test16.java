package com.arbonkeep.classloader;

/**
 * 演示扩展类加载器的特点
 */
public class Test16 {

    static {
        System.out.println("Test16 的静态代码块");
    }

    public static void main(String[] args) {
        System.out.println("Test1 classLoader : " + Test1.class.getClassLoader());

        System.out.println("Test16 classloader : " + Test16.class.getClassLoader());
    }

    /*
    我们通过将扩展类加载器的加载目录指定为当前目录来演示Test16的加载情况
        在指定类加载目录后，我们发现扩展类加载器并不能够将当前目录的Test16.class文件加载，
        而是需要将Test16.class文件打包成jar文件之后才能够通过扩展类加载器加载到该文件

        命令：
        java -Djava.ext.dirs=./：将扩展类加载器的目录指定为当前目录(修改系统属性java -D)

        jar cvf test.jar Test16.class:将Test16.class打包成test.jar

     */
}
