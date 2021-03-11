package initialize;

/**
 * @author liu peng bo
 * date: 2021/3/10 11:59
 */
public class TestDcl {
    private volatile static String a = get();

    public static String get() {
        System.out.println("get");
        return "a";
    }
}
