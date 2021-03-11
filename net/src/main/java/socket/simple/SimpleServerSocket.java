package socket.simple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * @author liu peng bo
 * @date 2019/2/27
 */
public class SimpleServerSocket {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(20000);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("accept!");
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder instr= new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                instr.append(line);
            }
            Thread.sleep(3000L);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(("hello "+instr).getBytes());
            outputStream.flush();
            socket.shutdownOutput();
            socket.close();
        }

        /*InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        socket.shutdownInput();*/
        //serverSocket.close();


    }
}
