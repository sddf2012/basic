package producerandconsumer;

/**
 * @author liu peng bo
 * @date 2019/2/18
 */
public class TestMyQueue {
    public static void main(String[] args) throws InterruptedException {
        MyQueue myQueue = new TwoLockConditionQueue();
        //MyQueue myQueue=new ConditionQueue();
        Thread producer = new Producer(myQueue, "p1");
        Thread producer2 = new Producer(myQueue, "p2");
        Thread producer3 = new Producer(myQueue, "p3");
        Thread consumer = new Consumer(myQueue, "c1");

        producer.start();
       /* producer2.start();
        producer3.start();*/
        consumer.start();

        /*Thread.sleep(5000);
        producer.interrupt();
        producer2.interrupt();
        producer3.interrupt();
        consumer.interrupt();
        System.out.println("等待次数:"+WaitNotifyQueue.WAITCOUNT.getCount());*/
    }
}
