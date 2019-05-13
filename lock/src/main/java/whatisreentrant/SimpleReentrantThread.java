package whatisreentrant;

/**
 * @author liu peng bo
 * @date 2019/2/19
 */
public class SimpleReentrantThread extends Thread {
    private SimpleReentrantEx simpleReentrantEx;

    public SimpleReentrantThread(String name, SimpleReentrantEx simpleReentrantEx) {
        super(name);
        this.simpleReentrantEx = simpleReentrantEx;
    }

    @Override
    public void run() {
        while (true) {
            try {
                simpleReentrantEx.doSome();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
