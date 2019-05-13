package exception;

/**
 * @author liu peng bo
 * @date 2019/3/22
 */
public class ExceptionEx {
    public static int i = 0;

    static int test() {
        try {
            int a = 1 / 0;
        } catch (Exception e) {
            return i;
        } finally {
            i++;
            return i;
        }
    }

    public static void main(String[] args) {
        System.out.println(test());
    }
}
