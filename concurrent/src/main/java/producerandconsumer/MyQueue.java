package producerandconsumer;

/**
 * @author liu peng bo
 * @date 2019/2/18
 */
public interface MyQueue {
    WaitCount WAITCOUNT = new WaitCount() ;

    void produce(String s) throws InterruptedException;

    String consume() throws InterruptedException;

}
