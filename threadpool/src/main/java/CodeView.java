import java.util.concurrent.*;

/**
 * @author liu peng bo
 * @date 2019/2/25
 */
public class CodeView {
    public static void main(String[] args) {
        //ExecutorService executorService= Executors.newFixedThreadPool(1);
        ExecutorService executorService=  new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                10L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("123");
            }
        });
        System.out.println("234");
    }
}
