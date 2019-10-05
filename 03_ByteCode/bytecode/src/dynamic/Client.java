package dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 创建客户端类
 */
public class Client {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        RealSubject rs = new RealSubject();//创建真实对象

        InvocationHandler ds = new DynamicSubject(rs);//创建动态代理对象

        Class<?> clazz = rs.getClass();//获取真实对象的class对象

        Subject subject = (Subject) Proxy.
                newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), ds);
        //第一个参数传入类加载，第二个参数表示所生成动态代理类所实现的所有接口，第三个参数InvocationHandler对象

        subject.request();

        //查看subject的真实类型
        System.out.println(subject.getClass());//class com.sun.proxy.$Proxy0

        //查看subject的真实类型的父类
        System.out.println(subject.getClass().getSuperclass());
    }
}
