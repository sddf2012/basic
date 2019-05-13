package producerandconsumer;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liu peng bo
 * @date 2019/2/20
 */
public class TwoLockConditionQueue implements MyQueue {
    private String[] array = new String[5];
    private AtomicInteger atomicInteger = new AtomicInteger();

    private int count, putIdx, takeIdx;

    private Lock putLock = new ReentrantLock();

    private Lock takeLock = new ReentrantLock();

    private Condition produceCondition = putLock.newCondition();

    private Condition consumeCondition = takeLock.newCondition();

    @Override
    public void produce(String s) throws InterruptedException {
        try {
            putLock.lock();
            System.out.println("");
            String name = Thread.currentThread().getName();
            System.out.println(name + " 当前队列大小:" + atomicInteger.get());
            while (atomicInteger.get() == array.length) {
                System.out.println(name + " 队列已满,开始等待!");
                WAITCOUNT.add();
                produceCondition.await();
            }
            array[putIdx] = s;
            if (++putIdx == array.length) {
                putIdx = 0;
            }
            System.out.println(Thread.currentThread().getName() + " 生产:" + putIdx + " " + s);
            int c = atomicInteger.getAndIncrement();
            if (c + 1 < array.length) {
                produceCondition.signal();
            }
            if (c == 0) {
                signalConsume();
            }

        } finally {
            putLock.unlock();
        }

    }

    public void signalConsume() {
        try {
            takeLock.lock();
            consumeCondition.signal();
        } finally {
            takeLock.unlock();
        }
    }

    @Override
    public String consume() throws InterruptedException {
        try {
            takeLock.lock();
            System.out.println("");
            String name = Thread.currentThread().getName();
            if (atomicInteger.get() == 0) {
                System.out.println(name + " 无数据,开始等待!");
                consumeCondition.await();
            }
            String result = array[takeIdx];
            System.out.println(name + " 消费" + takeIdx + " " + result);
            if (++takeIdx == array.length) {
                takeIdx = 0;
            }
            int c = atomicInteger.getAndDecrement();
            if (c > 1) {
                consumeCondition.signal();
            }
            if (c == array.length) {
                signalProduce();
            }
            return result;
        } finally {
            takeLock.unlock();
        }
    }

    public void signalProduce() {
        try {
            putLock.lock();
            produceCondition.signal();
        } finally {
            putLock.unlock();
        }
    }
}
