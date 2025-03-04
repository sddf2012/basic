package socket.thread;

import java.io.*;
import java.net.Socket;

/**
 * @author liu peng bo
 * @date 2019/2/27
 */
public class MultiSocketClient {
    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 20; i++) {
            Socket socket = new Socket("localhost", 20000);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);
            writer.write("tom" + i);
            writer.flush();
            socket.shutdownOutput();
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            socket.shutdownInput();
            socket.close();
            System.out.println("");
        }
    }
}
