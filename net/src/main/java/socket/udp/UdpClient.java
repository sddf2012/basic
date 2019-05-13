package socket.udp;

import java.io.IOException;
import java.net.*;

/**
 * @author liu peng bo
 * @date 2019/2/28
 */
public class UdpClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket=new DatagramSocket();
        byte[] s="hello 12312312312312313".getBytes();
        DatagramPacket packet=new DatagramPacket(s,s.length, InetAddress.getLocalHost(),10000);
        socket.send(packet);
        socket.close();
    }
}
