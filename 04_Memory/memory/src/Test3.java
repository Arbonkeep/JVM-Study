/**
 演示死锁
 */
public class Test3 {
    public static void main(String[] args) {
        new Thread(() -> A.method(), "Thread-A").start();

        new Thread(() -> B.method(), "Thread_B").start();

        try {
            Thread.sleep(40000);//让主线程休眠
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*
         分析：当第一个线程调用A的method，会先获取到A的锁，此时线程会休眠5秒，然后在方法中又调用B的method，所以会
              尝试获取B的锁。在线程A休眠的时候，B线程会获取B的锁，然后休眠5秒，调用B中方法，又尝试获取A的锁，此时
              就出现死锁的情况，两个线程进入了互相等待的情况

              注意：静态方法的锁（被static修饰的sycchronized的方法）是该方法对应类对象的锁

              此时，运行结果发现，两个类中的方法都有输出，但是程序没有停止，说明进入了死锁状态
         */
    }
}

class A {
    public static synchronized void method() {
        System.out.println("method from A");

        try {
            Thread.sleep(5000);
            B.method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class B {
    public  static synchronized void method() {
        System.out.println("method from B");
        try {
            Thread.sleep(5000);
            A.method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
