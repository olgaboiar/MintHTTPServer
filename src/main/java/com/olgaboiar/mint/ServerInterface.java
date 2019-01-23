package com.olgaboiar.mint;

import java.io.IOException;

public interface ServerInterface {
    void start() throws IOException;
    void perform() throws IOException;
    void acceptClientConnection() throws IOException;
    void listenToClientConnection() throws IOException;
    void closeClientConnection() throws IOException;
    void stop() throws IOException;
}
