package nio.buffer;

import java.io.FileNotFoundException;
import java.nio.CharBuffer;

/**
 * @author liu peng bo
 * @date 2019/2/28
 */
public class CharBufferEx {
    public static void main(String[] args) throws FileNotFoundException {
        /*RandomAccessFile file = new RandomAccessFile(new File("D:\\workspace\\basic\\basic\\net\\src\\main\\java\\nio\\buffer\\a.txt"), "rw");
        FileChannel channel = file.getChannel();*/
        CharBuffer charBuffer = CharBuffer.allocate(10);
        charBuffer.put(new char[]{'1', '2', '3'});
        System.out.println(charBuffer.capacity());
        System.out.println(charBuffer.position());
        System.out.println(charBuffer.limit());
        charBuffer.flip();
        System.out.println(charBuffer.get());
        System.out.println(charBuffer.get());
        charBuffer.rewind();
        System.out.println(charBuffer.get());
        charBuffer.put('a');
        charBuffer.flip();
        char[] result=new char[10];
        System.out.println("---");
        while (charBuffer.remaining()>=0){
            System.out.println(charBuffer.get());
        }
    }
}
