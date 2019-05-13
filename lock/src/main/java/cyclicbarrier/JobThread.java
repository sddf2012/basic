package cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author liu peng bo
 * @date 2019/2/20
 */
public class JobThread extends Thread {
    CyclicBarrier cyclicBarrier;
    long time;

    public JobThread(String name, CyclicBarrier cyclicBarrier,long time) {
        super(name);
        this.cyclicBarrier = cyclicBarrier;
        this.time=time;
    }

    @Override
    public void run() {
        try {
            Thread thread = Thread.currentThread();
            System.out.println(thread.getName() + " begin do");
            Thread.sleep(time);
            System.out.println(thread.getName() + " end do,begin wait");
            cyclicBarrier.await();
            System.out.println(thread.getName() + " do other!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int i = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(i);
        JobThread[] threads = new JobThread[i];
        for (int j = 0; j < i; j++) {
            JobThread jobThread = new JobThread("t" + j, cyclicBarrier,(j+1)*1000);
            threads[j] = jobThread;
            jobThread.start();
        }
        Thread.sleep(2000);
        threads[0].interrupt();
        Thread.sleep(7000);
        cyclicBarrier.reset();
        for (int j = 0; j < i; j++) {
            JobThread jobThread = new JobThread("s" + j, cyclicBarrier,0);
            jobThread.start();
        }
    }
}
