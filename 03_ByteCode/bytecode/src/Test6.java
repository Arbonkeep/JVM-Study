/**
 * 方法的动态分配
 *
 */
public class Test6 {
    public static void main(String[] args) {
        Fruit apple = new Apple();

        Fruit orange = new Orange();

        apple.test();//apple
        orange.test();//orange

        apple = new Orange();
        apple.test();//orange
    }
}

class Fruit {
    public void test() {
        System.out.println("fruit");
    }
}

class Apple extends Fruit {
    public void test() {
        System.out.println("Apple");
    }
}

class Orange extends Fruit {
    public void test() {
        System.out.println("Orange");
    }
}
