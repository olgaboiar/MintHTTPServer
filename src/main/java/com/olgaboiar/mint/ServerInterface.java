package com.olgaboiar.mint;

import java.io.IOException;

public interface ServerInterface {
    void start() throws IOException;
    void run() throws IOException;
    void acceptClientConnection() throws IOException;
    void listenToClientConnection() throws IOException;
    void readClientInput() throws IOException;
    void sendResponseToClient(String[] responseArray) throws IOException;
    void closeClientConnection() throws IOException;
    void stop() throws IOException;
}