package com.olgaboiar.mint;

import com.olgaboiar.mint.loggers.ILogger;

import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.olgaboiar.mint.Constants.DEFAULT_PORT;

public class Server {
    ILogger logger;
    static String date = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());
    IServerConnection serverConnection;
    Reader reader;
    Router router;


    public Server(IServerConnection serverConnection, ILogger logger, String routesConfigFilePath) throws IOException {
        this.serverConnection = serverConnection;
        this.logger = logger;
        this.router = new Router(createRoutes(routesConfigFilePath));
    }

    public void start() throws IOException {
        logger.logMessage(date + "\nServer started.");
        logger.logMessage("\nConnection on port " + DEFAULT_PORT);
        serverConnection.createServerSocket();
    }

    public void run() throws IOException {
        ISocketWrapper clientSocket = serverConnection.acceptClientConnection();
        BufferedReaderWrapper in = new BufferedReaderWrapper(serverConnection.listenToClientConnection(clientSocket));
        reader = new Reader(in);
        List<String> clientInput = reader.readInput();
        Request parsedRequest = new RequestBuilder(new RequestParser(reader)).buildRequest(clientInput);;
        Response response = router.route(parsedRequest);
        PrintWriter out = serverConnection.sendResponseToClient(response, clientSocket);
        logger.logMessage("\nResponse sent:\n" + response.prepareResponse());
        serverConnection.closeClientConnection(in, out, clientSocket);
    }

    private RouteMap createRoutes(String filePath) throws IOException {
        RoutesConfiguration routesConfiguration = new RoutesConfiguration(filePath);
        return new RouteMap(routesConfiguration);

    }

    public void stop() throws IOException {
        serverConnection.stop();
    }
}
