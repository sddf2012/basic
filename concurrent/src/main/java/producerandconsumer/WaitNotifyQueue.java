package producerandconsumer;

/**
 * @author liu peng bo
 * @date 2019/2/18
 */
public class WaitNotifyQueue implements MyQueue {
    private String[] array = new String[5];

    private int count, putIdx, takeIdx;

    @Override
    public void produce(String s) throws InterruptedException {
        synchronized (this) {
            System.out.println("");
            String name = Thread.currentThread().getName();
            System.out.println(name + " 当前队列大小:" + count);
            while (count == array.length) {
                System.out.println(name + " 队列已满,开始等待!");
                WAITCOUNT.add();
                this.wait();
            }
            array[putIdx] = s;
            System.out.println(Thread.currentThread().getName() + " 生产:" + putIdx + " " + s);

            count++;
            if (++putIdx == array.length) {
                putIdx = 0;
            }
            this.notify();
        }
    }

    @Override
    public String consume() throws InterruptedException {
        synchronized (this) {
            System.out.println("");
            String name = Thread.currentThread().getName();
            if (count == 0) {
                System.out.println(name + " 无数据,开始等待!");
                this.wait();
            }
            String result = array[takeIdx];
            System.out.println(name + " 消费" + takeIdx + " " + result);
            if (++takeIdx == array.length) {
                takeIdx = 0;
            }
            count--;
            this.notify();
            return result;
        }
    }

}
