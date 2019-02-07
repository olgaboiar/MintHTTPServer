package com.olgaboiar.mint;

import static com.olgaboiar.mint.Constants.*;

import java.io.IOException;
import java.net.Socket;

public class ServerRunner {

    public static void main(String[] args) throws IOException {
        Server server;
        ServerConnection serevrSocket = new ServerConnection();
        server = new Server(serevrSocket, DEFAULT_LOGGER);
        server.start();
        while(true) {
            server.run();
        }
    }
}
