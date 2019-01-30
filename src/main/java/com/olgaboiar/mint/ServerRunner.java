package com.olgaboiar.mint;

import static com.olgaboiar.mint.Constants.*;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ServerRunner {
    static String date = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());

    public static void main(String[] args) throws IOException {
        Server server;
        server = new Server(DEFAULT_HOST, DEFAULT_PORT);
        new Logger().logToFile(date + "\nServer started.", "logs.txt");
        server.start();
        while(true) {
            server.run();
        }

    }
}
