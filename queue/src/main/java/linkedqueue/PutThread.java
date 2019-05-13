package linkedqueue;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author liu peng bo
 * @date 2019/2/20
 */
public class PutThread extends Thread {
    LinkedBlockingQueue<Integer> queue;

    public PutThread(String name, LinkedBlockingQueue<Integer> queue) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int i = new Random().nextInt();
                queue.put(i);
                System.out.println(Thread.currentThread().getName() + " put " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
