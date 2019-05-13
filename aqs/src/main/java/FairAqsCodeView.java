import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liu peng bo
 * @date 2019/2/19
 */
public class FairAqsCodeView {
    static class Th extends Thread {
        ReentrantLock lock;
        long sleep;

        public Th(String name, ReentrantLock lock, long sleep) {
            super(name);
            this.lock = lock;
            this.sleep = sleep;
        }

        @Override
        public void run() {
            try {
                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + new Date()+ " lock");
                if (sleep > 0) {
                    Thread.sleep(sleep);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + new Date()+ " release");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Th t1 = new Th("t1", lock, 1000000);
        Th t11 = new Th("t11", lock, 0);
        Th t2 = new Th("t2", lock, 0);
        Th t3 = new Th("t3", lock, 0);

        System.out.println(new Date());
        t1.start();
        Thread.sleep(100);
        t11.start();
        Thread.sleep(100);
        t2.start();
        Thread.sleep(100);
        t3.start();
        Thread.sleep(1000);
        t2.interrupt();
        Thread.sleep(1000);
        t3.interrupt();
        System.out.println(new Date());

    }
}
