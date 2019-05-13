package other;

/**
 * @author liu peng bo
 * @date 2019/3/22
 */
public class ForEx {
    static void test1(){
        long begin=System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 1000; j++) {
                for (int k = 0; k < 100; k++) {

                }
            }
        }
        System.out.println("test1 "+(System.currentTimeMillis()-begin));
    }
    static void test2(){
        long begin=System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 1000; j++) {
                for (int k = 0; k < 10000; k++) {

                }
            }
        }
        System.out.println("test2 "+(System.currentTimeMillis()-begin));
    }
    static void test3(){
        long begin=System.currentTimeMillis();
        int i = 0,j=0,k=0;
        for (; i < 100; i++) {
            for ( j = 0; j < 1000; j++) {
                for ( k = 0; k < 10000; k++) {

                }
            }
        }
        System.out.println("test3 "+(System.currentTimeMillis()-begin));
    }
    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}
