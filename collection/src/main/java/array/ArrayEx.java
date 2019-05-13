package array;

/**
 * @author liu peng bo
 * @date 2019/3/20
 */
public class ArrayEx {
    static class Demo{
        int a;

        public Demo(int a) {
            this.a = a;
        }

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        @Override
        public String toString() {
            return String.valueOf(a);
        }
    }
    /**
     * 测试数组中存储的是引用还是对象
     * 结果是引用
     */
    public static void test1(){
        int[] a=new int[2];
        int b=1;
        a[0]=b;
        b=0;
        System.out.println(a[0]);

        String[] sArray=new String[1];
        String s1="a";
        sArray[0]=s1;
        s1=null;
        /*输出还是a，因为string是不可变对象，每次赋值实际上是等于新建一个string*/
        System.out.println(sArray[0]);

        Demo[] demos=new Demo[1];
        Demo demo=new Demo(2);
        demos[0]=demo;
        demo.setA(3);
        /*输出结果为3  说明存储的是引用,如果存储的是对象那么值不应该改变，因为如果存储对象，那么数组
        * 中的对象应该和创建时的对象是两个对象，因此存储对象的话就是浪费空间*/
        System.out.println(demos[0].toString());

    }

    public static void main(String[] args) {
        test1();
    }
}
