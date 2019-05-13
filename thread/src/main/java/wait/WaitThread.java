package wait;

/**
 * @author liu peng bo
 * @date 2019/2/18
 */
public class WaitThread extends Thread{
    private Object object;

    public WaitThread(Object object) {
        this.object = object;
    }

    @Override
    public void run() {
        synchronized (object){
            try {
                System.out.println("begin wait");
                object.wait();
                System.out.println("wait over!");
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
