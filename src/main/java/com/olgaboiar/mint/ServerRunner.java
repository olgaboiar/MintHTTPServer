package com.olgaboiar.mint;

import java.io.IOException;

public class ServerRunner {
    public static void main(String[] args) throws IOException {
        Server server;
//        String host = args[0];
        String host = "localhost";
        int port = 5000;
//        int port = Integer.parseInt(args[1]);
        server = new Server(host, port);
        System.out.println("starting");
        server.start();
        while(true) {
            server.run();
        }

    }
}
