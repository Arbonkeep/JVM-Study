package com.arbonkeep.classloader;

import com.sun.crypto.provider.AESKeyGenerator;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class Test13 {
    public static void main(String[] args) {
        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();

        System.out.println(aesKeyGenerator.getClass().getClassLoader());
        System.out.println(Test13.class.getClassLoader());

        /*
         通过打印结果我们知道aesKeyGenerator是通过扩展类加载器进行加载的，而Test13是通过系统类
         加载器进行加载的

         但是，如果我们通过java -D命令显示的将AESKeyGenerator的路径指定为当前路径的话，那么我们
         是不能够获取到aesKeyGenerator的字节码文件（也就是该类不能被加载，会报出异常）这是因为，
         指定的当前目录并没有AESKeyGenerator这个类

         命令如下：
            java -Djava.ext.dirs=./ com.arbonkeep.classloader.Test13
            就是会将java.ext.dirs指定为当前目录，并访问com.arbonkeep.classloader.Test13
         */

    }


}
