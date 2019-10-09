/**
 本类主要用于演示虚拟机栈的溢出
 （我们通过递归反复调用方法来达到效果）
 */

public class Test2 {
    private int length;

    public int getLength() {
        return length;
    }

    public void test() {
        this.length++;

        try {
            Thread.sleep(300);//让线程休眠一下，方便演示
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test();
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        //使用try...catch方便显示
        try {
            test2.test();
        } catch (Throwable ex) {
            System.out.println(test2.getLength());
            ex.printStackTrace();
        }

    }
}
