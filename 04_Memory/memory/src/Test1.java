import java.util.ArrayList;

/**
 * 演示堆内存溢出（不断往堆内存中添加对象）
 */
public class Test1 {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();

        while(true) {
            list.add(new Object());
            System.gc();//改动，主动调用垃圾回收器，发现内存溢出就消失了

            //当我们显示的调用垃圾回收器的时候，不会出现内存溢出，程序一直在运行
        }
    }

}
