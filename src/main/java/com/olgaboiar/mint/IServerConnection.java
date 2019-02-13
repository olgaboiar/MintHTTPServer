package com.olgaboiar.mint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public interface IServerConnection {

    void createServerSocket() throws IOException;

    Socket acceptClientConnection() throws IOException;

    BufferedReader listenToClientConnection(Socket clientSocket) throws IOException;

//    List<String> readClientInput(BufferedReader in) throws IOException;

    void closeClientConnection(BufferedReader in, PrintWriter out, Socket clientSocket) throws IOException;

    void stop() throws IOException;

    PrintWriter createOutPutStream(Socket clientSocket) throws IOException;

    PrintWriter sendResponseToClient(Response response, Socket clientSocket) throws IOException;
}
