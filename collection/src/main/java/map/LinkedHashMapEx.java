package map;

import java.util.LinkedHashMap;

/**
 * @author liu peng bo
 * @date 2019/3/24
 */
public class LinkedHashMapEx {
    public static void main(String[] args) {
        LinkedHashMap<Integer,Integer> map=new LinkedHashMap<>(16,0.75f,true);
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.get(2);
        map.forEach((k,v)->{
            System.out.println(k);
        });
    }
}
