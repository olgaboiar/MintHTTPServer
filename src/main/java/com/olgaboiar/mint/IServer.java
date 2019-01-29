package com.olgaboiar.mint;

import java.io.IOException;

public interface IServer {
    void start() throws IOException;
    void run() throws IOException;
    void acceptClientConnection() throws IOException;
    void listenToClientConnection() throws IOException;
    void readClientInput() throws IOException;
    void sendResponseToClient(Response response) throws IOException;
    void closeClientConnection() throws IOException;
    void stop() throws IOException;
}
