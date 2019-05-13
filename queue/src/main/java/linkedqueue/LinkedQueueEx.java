package linkedqueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author liu peng bo
 * @date 2019/2/20
 */
public class LinkedQueueEx {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);
        PutThread putThread = new PutThread("put", queue);
        TakeThread takeThread = new TakeThread("take", queue);
        putThread.start();
        Thread.sleep(3000);
        takeThread.start();
    }
}
