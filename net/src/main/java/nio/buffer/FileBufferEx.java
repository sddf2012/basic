package nio.buffer;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author liu peng bo
 * @date 2019/2/28
 */
public class FileBufferEx {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("D:\\workspace\\basic\\basic\\net\\src\\main\\java\\nio\\buffer\\a.txt", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        while (channel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            while (byteBuffer.remaining() > 0) {
                System.out.print((char) byteBuffer.get());
            }
            byteBuffer.clear();
        }
        byteBuffer.put("是的".getBytes());
        channel.write(byteBuffer);
        channel.force(true);
        channel.close();
    }
}
