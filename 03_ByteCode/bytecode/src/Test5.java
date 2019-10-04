/**
 * 方法的静态分派（如方法重载）
 *
 * GrandPa g1 = new Father();
 *
 * 以上代码，g1的静态类型是GrandPa，而g1的实际类型（真正指向的类型）为Father
 *
 * 我们可以得出这样一个结论：变量的静态类型是不会发生变化的，而变量的实际类型是可以发生变化的（多态的一种体现）
 * 实际类型是在运行期方可确定的。
 *
 *
 */
public class Test5 {

    //方法重载是一种静态行为，编译期就可以完全确定
    //而GrandPa是g1的静态类型，所以说最后是访问的grandpa
    public void test(GrandPa grandPa) {
        System.out.println("grandpa");
    }

    public void test(Father father) {
        System.out.println("father");
    }

    public void test(Son son) {
        System.out.println("son");
    }

    public static void main(String[] args) {
        GrandPa g1 = new Father();
        GrandPa g2 = new Son();

        Test5 test5 = new Test5();
        test5.test(g1);
        test5.test(g2);

        //在方法重载的时候，调用该方法，JVM唯一的判断依据就是根据方法本身接收参数的静态类型参数来去决定
        //调用哪一个方法。而不是根据实际类型来判断调用哪个方法
    }


}

class GrandPa{

}

class Father extends GrandPa {

}

class Son extends Father {

}