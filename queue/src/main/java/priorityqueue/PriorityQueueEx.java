package priorityqueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author liu peng bo
 * @date 2019/2/21
 */
public class PriorityQueueEx {
    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Integer> queue=new PriorityBlockingQueue<>();
        queue.put(1);
        queue.put(9);
        queue.put(8);
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
    }
}
