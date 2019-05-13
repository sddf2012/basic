package whatisreentrant;

/**
 * @author liu peng bo
 * @date 2019/2/19
 */
public class SimpleReentrantEx {
    private Thread lockThread;
    private int lockCount;
    private boolean lock;

    public synchronized void doSome() throws InterruptedException {
        try {
            lock();
            System.out.println("do something!");
        } finally {
            //unlock();
        }
    }

    public synchronized void lock() throws InterruptedException {
        Thread current = Thread.currentThread();
        while (lockThread != current && lock) {
            System.out.println(current.getName() + " 开始等待!");
            wait();
        }
        System.out.println(current.getName() + " 获取锁!");
        lock = true;
        lockCount++;
        lockThread = current;
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        if (lock && lockThread == current) {
            lockCount--;
            if (lockCount == 0) {
                lockThread = null;
                lock = false;
                System.out.println(current.getName() + " 释放锁");
                notify();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleReentrantEx ex = new SimpleReentrantEx();
        SimpleReentrantThread thread1 = new SimpleReentrantThread("s1", ex);
        SimpleReentrantThread thread2 = new SimpleReentrantThread("s2", ex);
        thread1.start();
        thread2.start();
        Thread.sleep(5000);
        thread1.interrupt();
        thread2.interrupt();
        System.out.println(ex.lockCount);

    }
}
