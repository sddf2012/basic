package reference;

/**
 * @author liu peng bo
 * @date 2019/3/22
 */
public class FinalizeEx {
    static Fex fex;

    class Fex {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize");
            fex=this;
            super.finalize();
        }
    }
    void test() throws InterruptedException {
        fex=new Fex();
        fex=null;
        System.out.println("第一次gc");
        System.gc();
        Thread.sleep(50);
        System.out.println(fex);
        fex=null;
        System.out.println("第二次gc");
        System.gc();
        Thread.sleep(50);
        System.out.println(fex);
    }

    public static void main(String[] args) throws InterruptedException {
        FinalizeEx ex=new FinalizeEx();
        ex.test();
    }
}
