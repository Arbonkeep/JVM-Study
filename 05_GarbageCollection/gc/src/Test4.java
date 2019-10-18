public class Test4 {
    public static void main(String[] args) throws InterruptedException {
        byte[] byte_1 = new byte[512 * 1024];//这两个字节数组是不会被回收的，会在from Survivor
        byte[] byte_2 = new byte[512 * 1024];//和to Survivor之间循环罐

        myGc();//此方法中的数组对象才会被回收
        Thread.sleep(1000);
        System.out.println("1111111111111");//区分执行流程使用

        myGc();
        Thread.sleep(1000);
        System.out.println("2222222222222");

        myGc();
        Thread.sleep(1000);
        System.out.println("3333333333333");

        myGc();
        Thread.sleep(1000);
        System.out.println("4444444444444");

        byte[] byte_3 = new byte[1024 * 1024];
        byte[] byte_4 = new byte[1024 * 1024];
        byte[] byte_5 = new byte[1024 * 1024];

        myGc();
        Thread.sleep(1000);
        System.out.println("5555555555555");

        myGc();
        Thread.sleep(1000);
        System.out.println("6666666666666");
        System.out.println("hello world");


    }

    public static void myGc() {//这里当方法调用结束后，就没有引用指向了新创建的字节数组，也就成为了垃圾
        for (int i = 0; i < 40 ; i++) {
            byte[] byteArray = new byte[1024 * 1024];
        }
    }
}
