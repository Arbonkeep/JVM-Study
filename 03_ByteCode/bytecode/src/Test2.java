/**
 * 用于自己练习
 */
public class Test2 {
    String str = "hello";

    private int x = 2;

    public static Integer i = 6;

    public void setX(int x) {
        this.x = x;
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();
        test2.setX(4);

        i = 8;
    }
}
