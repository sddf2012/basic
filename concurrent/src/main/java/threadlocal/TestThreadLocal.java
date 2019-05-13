package threadlocal;

/**
 * @author liu peng bo
 * @date 2019/2/18
 */
public class TestThreadLocal {
    static final ThreadLocal<Inner> threadLocal = new ThreadLocal<>();

    static class Inner {
        int i;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

    static class Th extends Thread {
        Inner inner;

        public Th(Inner inner) {
            this.inner = inner;
        }

        @Override
        public void run() {
            inner.setI(inner.getI() + 1);
            threadLocal.set(inner);
            System.out.println(threadLocal.get().getI());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Inner inner = new Inner();
        inner.setI(1);
        threadLocal.set(inner);
        Th t1 = new Th(inner);
        Th t2 = new Th(inner);
        t1.start();
        t2.start();
        Thread.sleep(100);
        System.out.println("main "+threadLocal.get().getI());
    }
}
