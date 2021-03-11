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
import java.net.SocketAddress;

/**
 * @author liu peng bo
 * date: 2021/2/25 11:47
 */
public class UdpServer extends JFrame implements ActionListener,Runnable {
    private JTextArea taMsg=new JTextArea("以下是聊天记录\n");
    private JTextField tfMsg=new JTextField("请您输入信息\n");
    private JButton btSend=new JButton("发送");
    private DatagramSocket ds=null;
    //保存好客户端的地址和端口
    private SocketAddress cAddress=null;
    public UdpServer() {
        this.setTitle("服务器端");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(taMsg, BorderLayout.CENTER);
        tfMsg.setBackground(Color.yellow);
        this.add(tfMsg,BorderLayout.NORTH);
        this.add(btSend,BorderLayout.SOUTH);
        btSend.addActionListener(this);
        this.setSize(200,300);
        this.setVisible(true);
        try {
            ds=new DatagramSocket(9999);
            new Thread(this).start();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                byte[] data=new byte[255];
                DatagramPacket dp=new DatagramPacket(data,data.length);
                ds.receive(dp);
                //保存客户端地址
                cAddress=dp.getSocketAddress();
                String msg=new String(dp.getData(),0,dp.getLength());
                System.out.println("服务端接收消息:"+msg);
                taMsg.append(msg+"\n");//添加内容
            }
        }
        catch (Exception ex) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String msg=tfMsg.getText();
            byte[] data=msg.getBytes();
            DatagramPacket dp=new DatagramPacket(data,data.length,cAddress);
            ds.send(dp);
            System.out.println("服务端发送消息:"+msg);
        }
        catch (Exception ex) {
        }
    }

    public static void main(String[] args) {
        new UdpServer();
    }
}
