package com.olgaboiar.mint;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements IServer {
    ServerSocket serverSocket;
    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    String host;
    static int port;
    RequestParser parser = new RequestParser();
    ResponseGenerator responseGenerator = new ResponseGenerator();
    Router router = new Router();
    Response response;

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
//        logger.setLevel(Level.FINE);
    }

    @Override
    public void run() throws IOException {
        acceptClientConnection();
        listenToClientConnection();
        readClientInput();
//        Response response = responseGenerator.generateResponse();
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
        System.out.println(list);
        Request currentRequest = parser.parse(list);
        response = router.route(currentRequest);

    }

    @Override
    public void sendResponseToClient(Response response) {
        out.println(response.prepareResponse());
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
