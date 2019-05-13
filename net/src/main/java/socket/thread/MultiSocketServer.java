package socket.thread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liu peng bo
 * @date 2019/2/27
 */
public class MultiSocketServer {
    static void multi() throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        ServerSocket serverSocket = new ServerSocket(20000);
        while (!Thread.interrupted()) {
            Socket socket = serverSocket.accept();
            executorService.submit(new SocketThread(socket));
        }
        serverSocket.close();
    }

    static class SocketThread extends Thread {
        Socket socket;

        public SocketThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            super.run();
            InputStream inputStream = null;
            try {
                inputStream = socket.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
                socket.shutdownInput();
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write((Thread.currentThread().getName() + " hello" + new Random().nextInt()).getBytes());
                socket.shutdownOutput();
                socket.close();
                System.out.println("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        multi();
    }


}
