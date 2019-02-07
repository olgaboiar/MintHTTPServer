package com.olgaboiar.mint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerConnection {
    ServerSocket serverSocket;

    public void createServerSocket(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public Socket acceptClientConnection() throws IOException {
        Socket clientSocket = serverSocket.accept();
        return clientSocket;
    }

    public BufferedReader listenToClientConnection(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return in;
    }

    public List<String> readClientInput(BufferedReader in) throws IOException {
        List<String> clientInput = new ArrayList<String>();
        String input = in.readLine();
        while (input.length() > 0) {
            clientInput.add(input);
            input = in.readLine();
        }
        return clientInput;
    }

    public void closeClientConnection(BufferedReader in, PrintWriter out, Socket clientSocket) throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }

    public void stop() throws IOException {
        serverSocket.close();
    }

    private PrintWriter createOutPutStream(Socket clientSocket) throws IOException {
        return new PrintWriter(clientSocket.getOutputStream());
    }

    public PrintWriter sendResponseToClient(Response response, Socket clientSocket) throws IOException {
        PrintWriter out = createOutPutStream(clientSocket);
        out.println(response.prepareResponse());
        out.flush();
        return out;
    }
}
