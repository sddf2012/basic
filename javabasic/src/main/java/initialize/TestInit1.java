package initialize;

/**
 * @author liu peng bo
 * date: 2021/3/10 10:55
 */
public class TestInit1 {

    public static class Inner {
        static {
            System.out.println("inner static block");
        }

        public static void m1() {
            System.out.println("inner m1");
        }
    }

    //public static TestInit2 testInit2 = new TestInit2();

    public static TestInit2 testInit23 = getTestInit2();

    static TestInit2 getTestInit2() {
        System.out.println("getTestInit2");
        return new TestInit2();
    }


    static {
        System.out.println("static block");
    }

    public static void m1() {
        System.out.println("m1");
    }

    public static void main(String[] args) {
        System.out.println("123123");
        //TestInit1.Inner.m1();
        //TestInit1.m1();
        TestInit1 init1=new TestInit1();
        TestInit1.testInit23.get();
        //TestInit1 init2=new TestInit1();

    }
}
