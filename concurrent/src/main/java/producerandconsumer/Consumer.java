package producerandconsumer;

/**
 * @author liu peng bo
 * @date 2019/2/18
 */
public class Consumer extends Thread {
    private MyQueue myQueue;

    Consumer(MyQueue myQueue, String name) {
        super(name);
        this.myQueue = myQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                myQueue.consume();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
