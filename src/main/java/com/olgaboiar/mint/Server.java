package com.olgaboiar.mint;

import com.olgaboiar.mint.ServerInterface;

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

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    @Override
    public void perform() throws IOException {
        acceptClientConnection();
        listenToClientConnection();
        List<String> list = new ArrayList<String>();
        String input = in.readLine();
        while (input.length() > 0) {
            System.out.println(input);
            list.add(input);
            input = in.readLine();
        }
        Request currentRequest = parser.parse(list);
        String method = currentRequest.getMethod();
        if (method.equals("GET")){
            out.println("HTTP/1.0 200 OK");
            out.println("Content-Type: text/html");
            out.println("");
        }
        out.flush();
        closeClientConnection();
    }

    @Override
    public void acceptClientConnection() throws IOException {
        clientSocket = serverSocket.accept();
    }

    @Override
    public void listenToClientConnection() throws IOException {
//        clientSocket = serverSocket.accept();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
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
