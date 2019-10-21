public class Test5 {

    public static void main(String[] args) {
        int size = 1024 * 1024;

        byte[] myAlloc1 = new byte[4 * size];
        System.out.println("1111111111111");

        byte[] myAlloc2 = new byte[4 * size];
        System.out.println("2222222222222");

        byte[] myAlloc3 = new byte[4 * size];
        System.out.println("3333333333333");


        byte[] myAlloc4 = new byte[2 * size];
        System.out.println("4444444444444");
    }
}
