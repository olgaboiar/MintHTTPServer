package com.olgaboiar.mint;

import static com.olgaboiar.mint.Constants.*;

import java.io.IOException;

public class ServerRunner {

    public static void main(String[] args) throws IOException {
        Server server;
        server = new Server(DEFAULT_HOST, DEFAULT_PORT);
        server.start();
        while(true) {
            server.run();
        }
    }
}
