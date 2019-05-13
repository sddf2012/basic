package producerandconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liu peng bo
 * @date 2019/2/18
 */
public class ConditionQueue implements MyQueue {
    private String[] array = new String[5];

    private int count, putIdx, takeIdx;

    private Lock lock = new ReentrantLock();

    private Condition produceCondition = lock.newCondition();

    private Condition consumeCondition = lock.newCondition();

    @Override
    public void produce(String s) throws InterruptedException {
        try {
            lock.lock();
            System.out.println("");
            String name = Thread.currentThread().getName();
            System.out.println(name + " 当前队列大小:" + count);
            while (count == array.length) {
                System.out.println(name + " 队列已满,开始等待!");
                WAITCOUNT.add();
                produceCondition.await();
            }
            array[putIdx] = s;
            System.out.println(Thread.currentThread().getName() + " 生产:" + putIdx + " " + s);

            count++;
            if (++putIdx == array.length) {
                putIdx = 0;
            }
            consumeCondition.signal();
        } finally {
            lock.unlock();
        }

    }

    @Override
    public String consume() throws InterruptedException {
        try {
            lock.lock();
            System.out.println("");
            String name = Thread.currentThread().getName();
            if (count == 0) {
                System.out.println(name + " 无数据,开始等待!");
                consumeCondition.await();
            }
            String result = array[takeIdx];
            System.out.println(name + " 消费" + takeIdx + " " + result);
            if (++takeIdx == array.length) {
                takeIdx = 0;
            }
            count--;
            produceCondition.signal();
            return result;
        } finally {
            lock.unlock();
        }
    }
}
