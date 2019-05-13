package deadlock;

/**
 * @author liu peng bo
 * @date 2019/2/19
 */
public class DeadLockEx1 {
    private Object o1 = new Object();
    private Object o2 = new Object();

    public void dosome() throws InterruptedException {
        Thread current = Thread.currentThread();
        synchronized (o1) {
            System.out.println(current.getName() + " 获取o1监视器");
            Thread.sleep(100);
            synchronized (o2) {
                System.out.println(current.getName() + " 获取o2监视器");
            }
        }
    }

    public void dosome2() throws InterruptedException {
        Thread current = Thread.currentThread();
        synchronized (o2) {
            System.out.println(current.getName() + " 获取o2监视器");
            Thread.sleep(100);
            synchronized (o1) {
                System.out.println(current.getName() + " 获取o1监视器");
            }
        }
    }

    static class InnerThread extends Thread {
        DeadLockEx1 deadLockEx1;

        public InnerThread(String name, DeadLockEx1 deadLockEx1) {
            super(name);
            this.deadLockEx1 = deadLockEx1;
        }

        @Override
        public void run() {
            try {
                deadLockEx1.dosome();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class InnerThread2 extends Thread {
        DeadLockEx1 deadLockEx1;

        public InnerThread2(String name, DeadLockEx1 deadLockEx1) {
            super(name);
            this.deadLockEx1 = deadLockEx1;
        }

        @Override
        public void run() {
            try {
                deadLockEx1.dosome2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DeadLockEx1 ex1 = new DeadLockEx1();
        InnerThread innerThread = new InnerThread("i1", ex1);
        InnerThread2 innerThread2 = new InnerThread2("i2", ex1);
        innerThread.start();
        innerThread2.start();
    }
}
