package com.olgaboiar.mint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MockServerConnection implements IServerConnection{
    @Override
    public void createServerSocket() throws IOException {

    }

    @Override
    public Socket acceptClientConnection() throws IOException {
        return null;
    }

    @Override
    public BufferedReader listenToClientConnection(Socket clientSocket) throws IOException {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void closeClientConnection(IBufferedReaderWrapper in, PrintWriter out, Socket clientSocket) throws IOException {

    }

    @Override
    public void stop() throws IOException {

    }

    @Override
    public PrintWriter createOutPutStream(Socket clientSocket) throws IOException {
        return null;
    }

    @Override
    public PrintWriter sendResponseToClient(Response response, Socket clientSocket) throws IOException {
        return null;
    }
}
