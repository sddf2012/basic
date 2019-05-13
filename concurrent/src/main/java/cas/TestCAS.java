package cas;

import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author liu peng bo
 * @date 2019/2/16
 */
public class TestCAS {
    int i;

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Field field = TestCAS.class.getDeclaredField("i");

        Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Unsafe unsafe = constructor.newInstance();
        long l= unsafe.objectFieldOffset(field);

        TestCAS testCAS=new TestCAS();
        unsafe.compareAndSwapInt(testCAS,l,1,2);
        System.out.println(testCAS.i);
    }
}
