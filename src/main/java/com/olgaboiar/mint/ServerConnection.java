package com.olgaboiar.mint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerConnection implements IServerConnection {
    ServerSocketWrapper serverSocket;
    int port;

    public ServerConnection(int port) {
        this.port = port;
    }

    @Override
    public void createServerSocket() throws IOException {
        serverSocket = new ServerSocketWrapper(port);
    }

    @Override
    public ISocketWrapper acceptClientConnection() throws IOException {
        return serverSocket.accept();
    }

    @Override
    public BufferedReader listenToClientConnection(ISocketWrapper clientSocket) throws IOException {
        BufferedReader in = clientSocket.getInputStream();
        return in;
    }

    @Override
    public void closeClientConnection(IBufferedReaderWrapper in, PrintWriter out, ISocketWrapper clientSocket) throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }

    @Override
    public void stop() throws IOException {
        serverSocket.close();
    }

    @Override
    public PrintWriter createOutPutStream(ISocketWrapper clientSocket) throws IOException {
        return clientSocket.getPrintWriter();
    }

    @Override
    public PrintWriter sendResponseToClient(Response response, ISocketWrapper clientSocket) throws IOException {
        PrintWriter out = createOutPutStream(clientSocket);
        out.print(response.prepareResponse());
        out.flush();
        return out;
    }
}
