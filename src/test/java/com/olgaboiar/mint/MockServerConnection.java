package com.olgaboiar.mint;

import java.io.*;

public class MockServerConnection implements IServerConnection {
    MockServerSocketWrapper mockServerSocket;
    PrintWriter printWriter;
    BufferedReader bufferedReader;

    public MockServerConnection(PrintWriter printWriter, BufferedReader bufferedReader) {
        this.printWriter = printWriter;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void createServerSocket() throws IOException {
        mockServerSocket = new MockServerSocketWrapper(new MockSocketWrapper(printWriter,bufferedReader));
    }

    @Override
    public ISocketWrapper acceptClientConnection() throws IOException {
        return mockServerSocket.accept();
    }

    @Override
    public BufferedReader listenToClientConnection(ISocketWrapper clientSocket) throws IOException {
        return clientSocket.getInputStream();
    }

    @Override
    public void closeClientConnection(IBufferedReaderWrapper in, PrintWriter out, ISocketWrapper clientSocket) throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }

    @Override
    public void stop() throws IOException {
        mockServerSocket.close();

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
