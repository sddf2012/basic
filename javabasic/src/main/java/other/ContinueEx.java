package other;

/**
 * @author liu peng bo
 * @date 2019/3/20
 */
public class ContinueEx {
    public static void continueTest() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 1 && j == 2) {
                    continue;
                }
                System.out.print(i + "*" + j + "=" + i * j + "    ");
            }
            System.out.println();
        }
    }
    public static void continueTest2() {
        loop:
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 1 && j == 2) {
                    continue loop;
                }
                System.out.print(i + "*" + j + "=" + i * j + "    ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        continueTest2();
    }
}
