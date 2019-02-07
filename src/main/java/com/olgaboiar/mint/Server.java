package com.olgaboiar.mint;

import com.olgaboiar.mint.loggers.ILogger;

import java.io.*;
import java.net.Socket;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.olgaboiar.mint.Constants.DEFAULT_PORT;

public class Server {
    ILogger logger;
    static String date = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());
    ServerConnection serverSocket;


    public Server(ServerConnection serverSocket, ILogger logger) {
        this.serverSocket = serverSocket;
        this.logger = logger;
    }

    public void start() throws IOException {
        logger.logMessage(date + "\nServer started.");
        logger.logMessage("\nConnection on port " + DEFAULT_PORT);
        serverSocket.createServerSocket(DEFAULT_PORT);
    }

    public void run() throws IOException {
        Socket clientSocket = serverSocket.acceptClientConnection();
        BufferedReader in = serverSocket.listenToClientConnection(clientSocket);
        List<String> clientInput = serverSocket.readClientInput(in);
        Request parsedRequest = parseRequest(clientInput);
        Response response = prepareResponse(parsedRequest);
        PrintWriter out = serverSocket.sendResponseToClient(response, clientSocket);
        logger.logMessage("\nResponse sent:\n" + response.prepareResponse());
        serverSocket.closeClientConnection(in, out, clientSocket);
    }

    private Request parseRequest(List<String> clientInput) throws IOException {
        Request currentRequest = new RequestParser().parse(clientInput);
        logger.logMessage("\nReceived request:\n" + clientInput);
        return currentRequest;
    }

    private Response prepareResponse(Request parsedRequest) throws IOException {
        RouteMap routeMap = new RouteMap();
        Response response = new Router(routeMap).route(parsedRequest);
        return response;
    }

    public void stop() throws IOException {
        serverSocket.stop();
    }
}
