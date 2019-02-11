package com.olgaboiar.mint;

import com.olgaboiar.mint.loggers.FileLogger;
import com.olgaboiar.mint.loggers.FileLoggerTest;
import com.olgaboiar.mint.loggers.ILogger;
import com.olgaboiar.mint.loggers.MockFileLogger;
import org.junit.jupiter.api.*;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServerTest {
    ILogger logger = new MockFileLogger();

    @BeforeAll
    public void setUp () throws Exception {
        MockServerConnection serverSocket = new MockServerConnection();
        Server testServer = new Server(serverSocket, logger);
        testServer.start();
    }

//    @Test
//    void testServerAnswersGetRequest () throws Exception {
//        String requestLine = "GET /simple_get HTTP/1.1";
//        InputStream stream = new ByteArrayInputStream((requestLine).getBytes(StandardCharsets.UTF_8));
//        System.setIn(stream);
//
//        String actual = new StringWriter().toString();
//        String expected = String.join("\r\n", new String[]{
//                "HTTP/1.1 200 OK",
//                "Content-Length: 0",
//                "",
//                "",
//                ""
//        });
//
//        assertEquals(expected, actual);
//    }

}
