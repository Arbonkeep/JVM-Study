import net.sf.cglib.proxy.Enhancer;
        import net.sf.cglib.proxy.MethodInterceptor;

public class Test4 {
    public static void main(String[] args) {
        for(;;) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Test4.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor)(obj, method, args1, proxy)
                    -> proxy.invokeSuper(obj,args1));

            System.out.println("hello word");

            enhancer.create();//通过程序不断创建Test4的子类，并会将元信息放置到元空间
        }
    }
}
