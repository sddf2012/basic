package producerandconsumer;

/**
 * @author liu peng bo
 * @date 2019/2/18
 */
public class Producer extends Thread{
    private MyQueue myQueue;

    public Producer(MyQueue myQueue, String name) {
        super(name);
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                myQueue.produce(String.valueOf(Math.random()));
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }

        }
    }
}
