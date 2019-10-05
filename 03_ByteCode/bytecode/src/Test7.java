import java.util.Date;

public class Test7 {
    public static void main(String[] args) {
        Animal animal = new Animal();
        Animal dog = new Dog();

        animal.test("hello");//Animal str,静态分派直接找到静态类型中的方法
        dog.test(new Date());//Dog date，动态分派会找到实际类型中的方法


    }
}

class Animal {
    public void test(String str) {
        System.out.println("Animal str");
    }

    public void test(Date date) {
        System.out.println("Animal date");
    }
}

class Dog extends Animal {
    public void test(String str) {
        System.out.println("Dog str");
    }

    public void test(Date date) {
        System.out.println("Dog date");
    }
}
