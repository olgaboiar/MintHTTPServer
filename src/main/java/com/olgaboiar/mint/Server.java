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
    Router router = new Router();
    Response response;
    Logger logger = new Logger();

    public Server(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        logger.logToFile("\nConnection on port " + port, "logs.txt");
    }

    @Override
    public void run() throws IOException {
        acceptClientConnection();
        listenToClientConnection();
        readClientInput();
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
        logger.logToFile("\nReceived request:\n" + list, "logs.txt");
        response = router.route(currentRequest);
    }

    @Override
    public void sendResponseToClient(Response response) throws IOException {
        out.println(response.prepareResponse());
        logger.logToFile("\nResponse sent:\n" + response.prepareResponse(), "logs.txt");
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
