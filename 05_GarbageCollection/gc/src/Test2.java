public class Test2 {
    public static void main(String[] args) {
        int size = 1024 * 1024;

        byte[] myAlloc = new byte[5 * size];//这里指定为5M，我们在JVM参数中指定的阈值为4M

        try {
            Thread.sleep(1000000);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
