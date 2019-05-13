package wait;

/**
 * @author liu peng bo
 * @date 2019/2/18
 */
public class TestWait {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        WaitThread thread1 = new WaitThread(o);
        WaitThread thread2 = new WaitThread(o);
        thread1.start();
        thread2.start();
        Thread.sleep(3000);
        synchronized (o) {
            o.notifyAll();
        }
    }
}
