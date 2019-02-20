package com.olgaboiar.mint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public interface IServerConnection {

    void createServerSocket() throws IOException;

    ISocketWrapper acceptClientConnection() throws IOException;

    BufferedReader listenToClientConnection(ISocketWrapper clientSocket) throws IOException;

    void closeClientConnection(IBufferedReaderWrapper in, PrintWriter out, ISocketWrapper clientSocket) throws IOException;

    void stop() throws IOException;

    PrintWriter createOutPutStream(ISocketWrapper clientSocket) throws IOException;

    PrintWriter sendResponseToClient(Response response, ISocketWrapper clientSocket) throws IOException;
}
