package udp;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author liu peng bo
 * date: 2021/2/25 11:46
 */
public class UdpClient extends JFrame implements ActionListener, Runnable {
    private JTextArea taMsg = new JTextArea("以下是聊天记录\n");
    private JTextField tfMsg = new JTextField("请您输入信息");
    private JButton btSend = new JButton("发送");
    private DatagramSocket ds = null;

    public UdpClient() {
        this.setTitle("客户端");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(taMsg, BorderLayout.CENTER);
        tfMsg.setBackground(Color.yellow);
        this.add(tfMsg, BorderLayout.NORTH);
        this.add(btSend, BorderLayout.SOUTH);
        btSend.addActionListener(this);
        this.setSize(200, 300);
        this.setVisible(true);
        try {
            ds = new DatagramSocket();
            InetAddress add = InetAddress.getByName("127.0.0.1");
            ds.connect(add, 9999);
            String msg = "客户端连接";
            byte[] data = msg.getBytes();
            DatagramPacket dp = new DatagramPacket(data, data.length);
            ds.send(dp);
            new Thread(this).start();
        } catch (Exception ex) {
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                byte[] data = new byte[255];
                DatagramPacket dp = new DatagramPacket(data, data.length);
                ds.receive(dp);
                String msg = new String(dp.getData(), 0, dp.getLength());
                System.out.println("客户端接收消息:"+msg);
                taMsg.append(msg + "\n"); //添加内容
            }
        } catch (Exception ex) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String msg = "客户端说：" + tfMsg.getText();
            byte[] data = msg.getBytes();
            DatagramPacket dp = new DatagramPacket(data, data.length);
            ds.send(dp);
            System.out.println("客户端发送消息:"+msg);
        } catch (Exception ex) {
        }
    }

    public static void main(String[] args) {
        new UdpClient();
    }
}
