package datatype.string;

/**
 * @author liu peng bo
 * @date 2019/3/20
 */
public class StringEx {
    /**
     * 该方法输出结果是false
     * test2 输出结果是true
     * 原因是：14行时会先创建abc的常量池对象  然后创建堆中对象
     * 而28行不会创建11的常量池对象，29行调用intern()后才会创建，而1.7之后支持常量池存储引用，所以常量池中直接存储堆中的引用
     */
    static void test2(){
        String str1 = new String("abc");
        System.out.println(str1.intern()==str1);
        String str2 = "abc";
        System.out.println(str1==str2);
    }
    static void test3(){
        String str1 = new StringBuilder("a").append("b").append("c").toString();
        System.out.println(str1.intern()==str1);
        String str2 = "abc";
        System.out.println(str1==str2);
    }

    static void test4(){
        String s3 = new String("1") + new String("1");    // 此时生成了四个对象 常量池中的"1" + 2个堆中的"1" + s3指向的堆中的对象（注此时常量池不会生成"11"）
        System.out.println(s3.intern()==s3);;    // jdk1.7之后，常量池不仅仅可以存储对象，还可以存储对象的引用，会直接将s3的地址存储在常量池
        String s4 = "11";    // jdk1.7之后，常量池中的地址其实就是s3的地址
        System.out.println(s3 == s4); // jdk1.7之前false， jdk1.7之后true
    }

    static void test5(){
        String temp="cd";
        String  s1 = "ab" + temp;
        String s2 = "abcd";
        System.out.println(s1 == s2);
    }
    public static void main(String[] args) {
        /*String str1 = "abc";
        String str2 = "abc";
        String str4 = new String("abc");
        String str5 = new String("abc");
        System.out.println(str1==str2);
        System.out.println(str1==str4);
        System.out.println(str4==str5);
        System.out.println(str4.intern()==str1);*/
        test2();
        test4();
    }
}
