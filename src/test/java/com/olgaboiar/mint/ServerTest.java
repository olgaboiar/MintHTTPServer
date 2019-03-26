//package com.olgaboiar.mint;
//
//import com.olgaboiar.mint.loggers.ILogger;
//import com.olgaboiar.mint.loggers.MockFileLogger;
//import org.junit.jupiter.api.*;
//
//import java.io.*;
//import java.time.ZonedDateTime;
//import java.time.format.DateTimeFormatter;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//class ServerTest {
//    String serverTestRoutesPath = "/testRoutes.yaml";
//    ILogger logger = new MockFileLogger();
//
//    @Test
//    public void serverAnswersSimpleGet() throws IOException {
//        String requestLine = "GET /test HTTP/1.1\nHost: 0.0.0.0:5000\n\nsome_body";
//        StringReader stringReader = new StringReader(requestLine);
//        BufferedReader bufferedReader = new BufferedReader(stringReader);
//        StringWriter stringWriter = new StringWriter();
//        String currentDate = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());
//        PrintWriter printWriter = new PrintWriter(stringWriter);
//        MockServerConnection serverConnection = new MockServerConnection(printWriter, bufferedReader);
//        Server server = new Server(serverConnection, logger, serverTestRoutesPath);
//        server.start();
//        server.run();
//        String actual = stringWriter.toString();
//        String expected =
//                "HTTP/1.1 200 OK\r\n" +
//                "Date: " + currentDate +
//                "\n\n";
//
//        assertEquals(expected, actual);
//    }
//}
