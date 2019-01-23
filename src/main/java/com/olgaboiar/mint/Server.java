package com.olgaboiar.mint;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    String host;
    static int port;
    ServerSocket serverSocket;
    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("start");

        while(true) {
            perform();
        }
    }

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
        String[] requestLineArr = list.get(0).split(" ");
        if (requestLineArr[0].equals("GET")){
            System.out.println("it's get");
            out.println("HTTP/1.0 200 OK");
            out.println("Content-Type: text/html");
            out.println("");
        }
        out.flush();
        closeClientConnection();
    }

    public void acceptClientConnection () throws IOException {
        clientSocket = serverSocket.accept();
    }

    public void listenToClientConnection() throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
    }

    public void closeClientConnection() throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }

    public void stop() throws IOException {
        serverSocket.close();
    }
}
