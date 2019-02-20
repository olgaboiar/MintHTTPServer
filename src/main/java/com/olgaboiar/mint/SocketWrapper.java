package com.olgaboiar.mint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketWrapper implements ISocketWrapper {
    Socket socket;

    public SocketWrapper(Socket socket) {
        this.socket = socket;
    }

    @Override
    public BufferedReader getInputStream() throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public PrintWriter getPrintWriter() throws IOException {
        return new PrintWriter(socket.getOutputStream());
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
