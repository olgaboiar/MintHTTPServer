package com.olgaboiar.mint;

import com.olgaboiar.mint.loggers.ILogger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Server {
    ServerSocket serverSocket;
    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    String host;
    static int port;
    RequestParser parser = new RequestParser();
    Router router = new Router();
//    Response response;
    ILogger logger;
    static String date = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());


    public Server(String host, int port, ILogger logger) {
        this.host = host;
        this.port = port;
        this.logger = logger;
    }

    public void start() throws IOException {
        serverSocket = new ServerSocket(port);
        logger.logMessage(date + "\nServer started.");
        logger.logMessage("\nConnection on port " + port);
    }

    public void run() throws IOException {
        acceptClientConnection();
        listenToClientConnection();
        List<String> clientInput = readClientInput();
        Request parsedRequest = parseRequest(clientInput);
        Response response = prepareResponse(parsedRequest);
        sendResponseToClient(response);
        closeClientConnection();
    }

    private void acceptClientConnection() throws IOException {
        clientSocket = serverSocket.accept();
    }

    private void listenToClientConnection() throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        out = new PrintWriter(clientSocket.getOutputStream());
    }

    private List<String> readClientInput() throws IOException {
        List<String> clientInput = new ArrayList<String>();
        String input = in.readLine();
        while (input.length() > 0) {
            clientInput.add(input);
            input = in.readLine();
        }
        return clientInput;
    }

    private Request parseRequest(List<String> clientInput) throws IOException {
        Request currentRequest = parser.parse(clientInput);
        logger.logMessage("\nReceived request:\n" + clientInput);
        return currentRequest;
    }

    private Response prepareResponse(Request parsedRequest) throws IOException {
        Response response = router.route(parsedRequest);
        return response;
    }

    private void sendResponseToClient(Response response) throws IOException {
        out = new PrintWriter(clientSocket.getOutputStream());
        out.println(response.prepareResponse());
        logger.logMessage("\nResponse sent:\n" + response.prepareResponse());
        out.flush();
    }

    private void closeClientConnection() throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }

    public void stop() throws IOException {
        serverSocket.close();
    }
}
