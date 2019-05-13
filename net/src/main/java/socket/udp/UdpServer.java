package socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author liu peng bo
 * @date 2019/2/28
 */
public class UdpServer {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(10000);
        byte[] all=new byte[2000];
        byte[] b = new byte[1000];
        DatagramPacket packet = new DatagramPacket(b, b.length);
        boolean over = false;
        //while (!over) {
            socket.receive(packet);
            packet.getData().toString();
           // packet.setLength(b.length);
       // }
        System.out.println(socket.getReceiveBufferSize());

        System.out.println(packet.getLength());
        String s = new String(b);
        System.out.println(s);
        socket.close();
    }
}
