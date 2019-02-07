package com.olgaboiar.mint;

import static com.olgaboiar.mint.Constants.*;

import java.io.IOException;
import java.net.Socket;

public class ServerRunner {

    public static void main(String[] args) throws IOException {
        Server server;
        ServerConnection serverSocket = new ServerConnection(DEFAULT_PORT);
        server = new Server(serverSocket, DEFAULT_LOGGER);
        server.start();
        while(true) {
            server.run();
        }
    }
}
