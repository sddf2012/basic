package nio.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author liu peng bo
 * @date 2019/3/1
 */
public class NioClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("0.0.0.0", 20000));
        //Selector selector=Selector.open();
        /*ByteBuffer byteBuffer = ByteBuffer.allocate(200);
        byteBuffer.put("hello".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        //socketChannel.shutdownOutput();
        ByteBuffer receive = ByteBuffer.allocate(500);
        int i = 0;
        while (i <= 0) {
            i = socketChannel.read(receive);
        }
        receive.flip();
        byte[] result=new byte[i];
        receive.get(result);
        System.out.println(new String(result));

        //socketChannel.shutdownInput();
        socketChannel.close();*/
    }
}
