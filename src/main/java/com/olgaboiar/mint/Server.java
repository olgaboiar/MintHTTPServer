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
        List<String> list = new ArrayList<String>();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream());
        BufferedOutputStream dataOut = new BufferedOutputStream(clientSocket.getOutputStream());
        String input = in.readLine();
        while (input.length() > 0) {
            System.out.println(input);
            list.add(input);
            input = in.readLine();
        }
        System.out.println(list);
        System.out.println(list.get(0));
        String[] requestLineArr = list.get(0).split(" ");
        System.out.println(requestLineArr[0]);
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
        System.out.println("connected");

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
