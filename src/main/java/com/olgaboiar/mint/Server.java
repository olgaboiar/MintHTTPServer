package com.olgaboiar.mint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements ServerInterface {
    ServerSocket serverSocket;
    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    String host;
    static int port;
    RequestParser parser = new RequestParser();
    ResponseGenerator responseGenerator = new ResponseGenerator();

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void run() throws IOException {
        acceptClientConnection();
        listenToClientConnection();
        readClientInput();
        String[] responseArray = responseGenerator.generateResponse();
        sendResponseToClient(responseArray);
        closeClientConnection();
    }

    @Override
    public void acceptClientConnection() throws IOException {
        clientSocket = serverSocket.accept();
    }

    @Override
    public void listenToClientConnection() throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
    }

    @Override
    public void readClientInput() throws IOException {
        List<String> list = new ArrayList<String>();
        String input = in.readLine();
        while (input.length() > 0) {
            list.add(input);
            input = in.readLine();
        }
    }

    @Override
    public void sendResponseToClient(String[] responseArray) {
        for (String item : responseArray) {
            out.println(item);
        }
        out.flush();
    }

    @Override
    public void closeClientConnection() throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }

    @Override
    public void stop() throws IOException {
        serverSocket.close();
    }
}
