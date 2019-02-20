package com.olgaboiar.mint;

import static com.olgaboiar.mint.Constants.*;

import java.io.IOException;

public class ServerRunner {
    static int port;
    static Server server;

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {
            port = Integer.valueOf(args[0]);
        } else {
            port = DEFAULT_PORT;
        }
        ServerConnection serverConnection = new ServerConnection(port);
        String serverRoutes = DEFAULT_ROUTES;
        server = new Server(serverConnection, DEFAULT_LOGGER, serverRoutes);
        server.start();
        while(true) {
            server.run();
        }
    }
}
