package reference;

import java.lang.ref.*;
import java.lang.reflect.Field;

/**
 * @author liu peng bo
 * @date 2019/3/22
 * -Xmx2m -Xms2m -XX:+PrintGCDetails
 */
public class RefEx {
    static class Bean {
        int i;
        byte[] bytes;

        public Bean(int i) {
            this.i = i;
        }

        public Bean(int i, byte[] bytes) {
            this.i = i;
            this.bytes = bytes;
        }

        public Bean(byte[] bytes) {
            this.bytes = bytes;
        }

        public int getI() {
            return i;
        }

        @Override
        public String toString() {
            return "Bean{" +
                    "i=" + i +
                    '}';
        }
    }

    static class QueueThread extends Thread {
        ReferenceQueue queue;

        public QueueThread(ReferenceQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                Object obj = queue.poll();
                if (obj != null) {
                    try {
                        Object result;
                        if (obj instanceof SoftReference || obj instanceof WeakReference) {
                            result = ((Reference) obj).get();
                        } else {
                            Field rereferent = Reference.class
                                    .getDeclaredField("referent");
                            rereferent.setAccessible(true);
                            result = rereferent.get(obj);
                        }
                        System.out.println("gc will collect："
                                + result.getClass() + "@"
                                + result.hashCode() + "\t"
                                + result.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    /**
     * 对象既关联了软引用或者弱引用，也有强引用，则对象不会回收
     * 该方法后半部分会报错
     */
    static void testSoft() {
        SoftReference[] softArr = new SoftReference[5];
        /*softArr[0] = new SoftReference<byte[]>(new byte[200 * 1024]);
        System.out.println("GC 前===>" + softArr[0].get());
        System.gc();
        System.out.println("第一次GC后：===>" + softArr[0].get());
        softArr[1] = new SoftReference<byte[]>(new byte[200 * 1024]);
        System.gc();
        System.out.println("第二次GC后===>" + softArr[0].get());
        softArr[2] = new SoftReference<byte[]>(new byte[200 * 1024]);
        System.gc();
        System.out.println("第三次GC后===>" + softArr[0].get());
        softArr[3] = new SoftReference<byte[]>(new byte[200 * 1024]);
        //System.gc();  这里都不需要显示执行，因为堆内存已经满了，虚拟机自己会执行。
        System.out.println("第四次GC后===>" + softArr[0].get());*/

        Bean b1 = new Bean(new byte[200 * 1024]);
        softArr[0] = new SoftReference<>(b1);
        System.out.println("GC 前===>" + softArr[0].get());
        System.gc();
        System.out.println("第一次GC后：===>" + softArr[0].get());
        Bean b2 = new Bean(new byte[200 * 1024]);
        softArr[1] = new SoftReference<>(b2);
        System.gc();
        System.out.println("第二次GC后===>" + softArr[0].get());
        Bean b3 = new Bean(new byte[200 * 1024]);
        softArr[2] = new SoftReference<>(b3);
        System.gc();
        System.out.println("第三次GC后===>" + softArr[0].get());
        Bean b4 = new Bean(new byte[200 * 1024]);
        softArr[3] = new SoftReference<>(b4);
        System.gc();
        System.out.println("第四次GC后===>" + softArr[0].get());

    }

    static void testSoft2() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        Bean bean = new Bean(2);
        ReferenceQueue queue = new ReferenceQueue();
        QueueThread thread = new QueueThread(queue);
        thread.start();
        SoftReference<Bean> phantomReference = new SoftReference<Bean>(bean, queue);
        System.out.println("GC 前===>" + phantomReference);
        bean = null;
        System.gc();
    }

    static void testWeak() {
        WeakReference[] softArr = new WeakReference[5];
        /*softArr[0] = new WeakReference<byte[]>(new byte[200 * 1024]);
        System.out.println("GC 前===>" + softArr[0].get());
        System.gc();
        System.out.println("第一次GC后：===>" + softArr[0].get());
        softArr[1] = new WeakReference<byte[]>(new byte[200 * 1024]);
        System.gc();
        System.out.println("第二次GC后===>" + softArr[0].get());
        softArr[2] = new WeakReference<byte[]>(new byte[200 * 1024]);
        System.gc();
        System.out.println("第三次GC后===>" + softArr[0].get());
        softArr[3] = new WeakReference<byte[]>(new byte[200 * 1024]);
        System.gc();
        System.out.println("第四次GC后===>" + softArr[0].get());*/

        byte[] b1 = new byte[200 * 1024];
        softArr[0] = new WeakReference<byte[]>(b1);
        System.out.println("GC 前===>" + softArr[0].get());
        System.gc();
        System.out.println("第一次GC后：===>" + softArr[0].get());
        byte[] b2 = new byte[200 * 1024];
        softArr[1] = new WeakReference<byte[]>(b2);
        System.gc();
        System.out.println("第二次GC后===>" + softArr[0].get());
        byte[] b3 = new byte[200 * 1024];
        softArr[2] = new WeakReference<byte[]>(b3);
        System.gc();
        System.out.println("第三次GC后===>" + softArr[0].get());
        byte[] b4 = new byte[200 * 1024];
        softArr[3] = new WeakReference<byte[]>(b4);
        System.gc();
        System.out.println("第四次GC后===>" + softArr[0].get());
    }

    static void testWeak2() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        //Bean bean = new Bean(2);
        ReferenceQueue queue = new ReferenceQueue();
        QueueThread thread = new QueueThread(queue);
        thread.start();
        WeakReference<Bean> phantomReference = new WeakReference<Bean>(new Bean(2), queue);
        System.out.println("GC 前===>" + phantomReference.get());
        //bean=null;
        System.gc();
    }

    /**
     * gc不会回收虚引用
     */
    static void testPhantom() {
        PhantomReference[] softArr = new PhantomReference[5];
        ReferenceQueue queue = new ReferenceQueue();
        softArr[0] = new PhantomReference<byte[]>(new byte[200 * 1024], queue);
        System.out.println("GC 前===>" + softArr[0].get());
        System.gc();
        System.out.println("第一次GC后：===>" + softArr[0].get());
        softArr[1] = new PhantomReference<byte[]>(new byte[200 * 1024], queue);
        System.gc();
        System.out.println("第二次GC后===>" + softArr[0].get());
        softArr[2] = new PhantomReference<byte[]>(new byte[200 * 1024], queue);
        System.gc();
        System.out.println("第三次GC后===>" + softArr[0].get());
        softArr[3] = new PhantomReference<byte[]>(new byte[200 * 1024], queue);
        System.gc();
        System.out.println("第四次GC后===>" + softArr[0].get());
    }

    static void testPhantom2() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        Bean bean = new Bean(2);
        ReferenceQueue queue = new ReferenceQueue();
        PhantomReference<Bean> phantomReference = new PhantomReference<Bean>(bean, queue);
        QueueThread thread = new QueueThread(queue);
        thread.start();
        System.out.println("GC 前===>" + phantomReference);
        bean = null;
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        //testSoft2();
        testWeak2();
        //testPhantom2();
        //testWeak();
        //testSoft();
    }
}
