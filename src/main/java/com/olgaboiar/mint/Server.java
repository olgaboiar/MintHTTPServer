package com.olgaboiar.mint;

import java.io.*;
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
        Response response = responseGenerator.generateResponse();
        sendResponseToClient(response);
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
        Request currentRequest = parser.parse(list);
        String method = currentRequest.getMethod();
        String file = "." + currentRequest.getRequestedFile();
        System.out.println(method);
        System.out.println(file);
        File searchForFile = new File(file);
        System.out.println(searchForFile.isFile());
        if (searchForFile.isFile()) {
            System.out.println("file exists, append it to response body");
        } else {
            System.out.println("file doesn't exist, give not found error");
        }

    }

    @Override
    public void sendResponseToClient(Response response) {

        out.println(response.getStatusLine());
        out.println(response.getContentType());
        out.println("");
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
