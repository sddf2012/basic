package socket.simple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liu peng bo
 * @date 2019/2/27
 */
public class SimpleServerSocket {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(20000);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        socket.shutdownInput();

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello".getBytes());
        socket.shutdownOutput();
        socket.close();
        serverSocket.close();
    }
}
