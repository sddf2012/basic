package singleton;

/**
 * @author liu peng bo
 * @date 2019/3/13
 */
public class Singleton1 {

    private Singleton1() {
    }

    private static  Singleton1 singleton;

    public static Singleton1 get() {
        if (singleton == null) {
            synchronized (Singleton1.class) {
                if (singleton == null) {
                    singleton = new Singleton1();
                }
            }
        }
        return singleton;
    }
}
