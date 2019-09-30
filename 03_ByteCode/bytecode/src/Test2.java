public class Test2 {

    String str = "Welcome";

    private int x = 5;

    public static Integer in = 10;

    public static void main(String[] args) {
        Test2 test2 = new Test2();

        test2.setX(8);

        in = 20;
    }

    public Test2() {

    }

    public Test2(int i) {

    }

    private synchronized void setX(int x) {
        this.x = x;
    }

    private void test(String str) {
        synchronized (str) {
            System.out.println("hello world");
        }
    }

    private  synchronized  static void test2() {

    }
}
/**
    如果有多个构造方法，那么每个构造方法中都会进行变量的赋值操作

    如果一个类没有构造方法，编译器会默认生成构造方法，同时将成员变量的赋值放在默认构造方法中，
    如果自己提供了构造方法，编译器在编译完之后依然会将赋值放在自己的构造方法中

 */