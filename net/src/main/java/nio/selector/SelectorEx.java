package nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author liu peng bo
 * @date 2019/2/28
 */
public class SelectorEx {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(20001));
        //channel.configureBlocking(false);
        channel.accept();
        channel.register(selector, SelectionKey.OP_READ);
        Set<SelectionKey> set= selector.selectedKeys();
        Iterator<SelectionKey> iterator=set.iterator();
        while (iterator.hasNext()){
            SelectionKey selectionKey= iterator.next();
            selectionKey.isAcceptable();
        }

    }
}
