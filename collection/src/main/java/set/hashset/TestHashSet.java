package set.hashset;

import java.util.HashSet;

/**
 * @author liu peng bo
 * @date 2019/2/17
 */
public class TestHashSet {
    public static void main(String[] args) {
        HashSet<String> strings=new HashSet<>();
        System.out.println(strings.add("a"));
        System.out.println(strings.add("a"));
    }
}
