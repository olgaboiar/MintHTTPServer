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
    String host;
    static int port;
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
        Socket clientSocket = acceptClientConnection();
        BufferedReader in = listenToClientConnection(clientSocket);
        List<String> clientInput = readClientInput(in);
        Request parsedRequest = parseRequest(clientInput);
        Response response = prepareResponse(parsedRequest);
        PrintWriter out = sendResponseToClient(response, clientSocket);
        closeClientConnection(in, out, clientSocket);
    }

    private Socket acceptClientConnection() throws IOException {
        Socket clientSocket = serverSocket.accept();
        return clientSocket;
    }

    private BufferedReader listenToClientConnection(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return in;
    }

    private List<String> readClientInput(BufferedReader in) throws IOException {
        List<String> clientInput = new ArrayList<String>();
        String input = in.readLine();
        while (input.length() > 0) {
            clientInput.add(input);
            input = in.readLine();
        }
        return clientInput;
    }

    private Request parseRequest(List<String> clientInput) throws IOException {
        Request currentRequest = new RequestParser().parse(clientInput);
        logger.logMessage("\nReceived request:\n" + clientInput);
        return currentRequest;
    }

    private Response prepareResponse(Request parsedRequest) throws IOException {
        Response response = new Router().route(parsedRequest);
        return response;
    }

    private PrintWriter createOutPutStream(Socket clientSocket) throws IOException {
        return new PrintWriter(clientSocket.getOutputStream());
    }

    private PrintWriter sendResponseToClient(Response response, Socket clientSocket) throws IOException {
        PrintWriter out = createOutPutStream(clientSocket);
        out.println(response.prepareResponse());
        logger.logMessage("\nResponse sent:\n" + response.prepareResponse());
        out.flush();
        return out;
    }

    private void closeClientConnection(BufferedReader in, PrintWriter out, Socket clientSocket) throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }

    public void stop() throws IOException {
        serverSocket.close();
    }
}
