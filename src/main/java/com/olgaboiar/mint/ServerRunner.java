package com.olgaboiar.mint;

import static com.olgaboiar.mint.Constants.*;

import java.io.IOException;

public class ServerRunner {

    public static void main(String[] args) throws IOException {
        ServerConnection serverSocket = new ServerConnection(DEFAULT_PORT);
        Server server = new Server(serverSocket, DEFAULT_LOGGER);
        server.start();
        while(true) {
            server.run();
        }
    }
}
