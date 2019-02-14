package com.olgaboiar.mint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnection implements IServerConnection {
    ServerSocket serverSocket;
    int port;

    public ServerConnection(int port) {
        this.port = port;
    }

    @Override
    public void createServerSocket() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public Socket acceptClientConnection() throws IOException {
        Socket clientSocket = serverSocket.accept();
        return clientSocket;
    }

    @Override
    public BufferedReader listenToClientConnection(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return in;
    }

    @Override
    public void closeClientConnection(BufferedReader in, PrintWriter out, Socket clientSocket) throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }

    @Override
    public void stop() throws IOException {
        serverSocket.close();
    }

    @Override
    public PrintWriter createOutPutStream(Socket clientSocket) throws IOException {
        return new PrintWriter(clientSocket.getOutputStream());
    }

    @Override
    public PrintWriter sendResponseToClient(Response response, Socket clientSocket) throws IOException {
        PrintWriter out = createOutPutStream(clientSocket);
        out.print(response.prepareResponse());
        out.flush();
        return out;
    }
}
