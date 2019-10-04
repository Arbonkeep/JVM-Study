/**
 1. invokeinterface：调用接口中的方法。实际上是在运行期决定的，决定到底调用实现接口的哪个对象的特定方法

 2. invokestatic：调用静态方法

 3. invokespecial：调用自己的私有的方法、构造方法（<init>）以及父类的方法

 4. invokevirtual：调用虚方法。运行期栋态查找的过程

 5. invokedynamic：动态调用方法
 */
public class Test4 {
    public static void test() {
        System.out.println("test invoked");
    }

    public static void main(String[] args) {
        test();
    }
}
