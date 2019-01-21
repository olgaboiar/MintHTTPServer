import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    String host;
    static int port;
    ServerSocket serverSocket;

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        while(true) {
//            do smth
        }
    }

    public void stop() throws IOException {
        serverSocket.close();
    }
}
