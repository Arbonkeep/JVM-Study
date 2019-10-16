public class Test1 {

    public static void main(String[] args) {
        int size = 1024 * 1024;

        byte[] myAlloc1 = new byte[1 * size];//原生数组（里面的对象都是0）,如果是引用类型的数组，那么都是null
        byte[] myAlloc2 = new byte[1 * size];
        byte[] myAlloc3 = new byte[3 * size];
        byte[] myAlloc4 = new byte[3 * size];



        System.out.println("hello world");

//        byte[] myAlloc4 = new byte[2 * size];
//        byte[] myAlloc5 = new byte[2 * size];
//        byte[] myAlloc6 = new byte[2 * size];//通过添加可以造成FullGC

//        for (byte b : myAllocl) {
//            System.out.println(b);
//        }





    }
}
