import java.io.IOException;

public class ServerRunner {
    public static void main(String[] args) throws IOException {
        Server server;
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        server = new Server(host, port);
        server.start();
    }
}
