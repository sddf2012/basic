package nio.simple;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liu peng bo
 * @date 2019/3/1
 */
public class NioServer {
    private int port;

    public NioServer(int port) throws IOException {
        this.port = port;
    }

    public void server() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> set = selector.selectedKeys();
            Iterator<SelectionKey> iterator = set.iterator();
            while (iterator.hasNext()) {
                SelectionKey selection = iterator.next();
                if (selection.isAcceptable()) {
                    handleAccept(selection);
                }
                if (selection.isReadable()) {
                    handleRead(selection);
                }
                if (selection.isValid()&&selection.isWritable()) {
                    handleWrite(selection);
                }
                iterator.remove();
            }
        }
    }

    public void handleAccept(SelectionKey selection) throws IOException {
        ServerSocketChannel server = (ServerSocketChannel) selection.channel();
        SocketChannel socketChannel = server.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selection.selector(), SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }

    public void handleRead(SelectionKey selection) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selection.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(200);
        socketChannel.read(byteBuffer);
        byteBuffer.flip();
        byte[] receive = byteBuffer.array();
        System.out.println("receive:" + new String(receive));
        byteBuffer.clear();

        byteBuffer.put("服务端已经收到了你的请求".getBytes());
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
        socketChannel.shutdownOutput();
        socketChannel.close();
    }

    public void handleWrite(SelectionKey selection){
        System.out.println("handle write");
    }

    public static void main(String[] args) throws IOException {
        NioServer server = new NioServer(20000);
        server.server();
    }
}
