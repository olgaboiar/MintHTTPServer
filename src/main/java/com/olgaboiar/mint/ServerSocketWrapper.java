package com.olgaboiar.mint;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketWrapper implements IServerSocketWrapper {
    ServerSocket serverSocket;

    public ServerSocketWrapper(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    @Override
    public ISocketWrapper accept() throws IOException {
        Socket socket = serverSocket.accept();
        return new SocketWrapper(socket);
    }

    @Override
    public void close() throws IOException {
        serverSocket.close();
    }
}
