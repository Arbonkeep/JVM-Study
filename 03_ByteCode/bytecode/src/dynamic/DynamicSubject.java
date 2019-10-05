package dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSubject implements InvocationHandler {//必须实现InvocationHandler接口
    private Object obj;//将代理的真实对象作为成员变量

    public DynamicSubject(Object obj) {//通过构造方法将真实对象传递到本类
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before calling： " + method);

        method.invoke(this.obj, args);//实现对目标方法的真实调用，在之前或之后可以实现其他逻辑

        System.out.println("after calling： " + method);

        return null;
    }
}
