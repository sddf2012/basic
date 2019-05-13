package linkedqueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author liu peng bo
 * @date 2019/2/20
 */
public class TakeThread extends Thread {
    LinkedBlockingQueue<Integer> queue;

    public TakeThread(String name, LinkedBlockingQueue<Integer> queue) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int i = queue.take();
                System.out.println(Thread.currentThread().getName() + " take " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
