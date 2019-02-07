package com.olgaboiar.mint;

import com.olgaboiar.mint.loggers.FileLogger;
import com.olgaboiar.mint.loggers.FileLoggerTest;
import com.olgaboiar.mint.loggers.ILogger;
import com.olgaboiar.mint.loggers.MockFileLogger;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServerTest {
    Server testServer;
    String host = "localhost";
    int port = 5000;
    ILogger logger = new MockFileLogger();

    @BeforeAll
    public void setUp () throws Exception {
        ServerConnection serverSocket = new ServerConnection(5000);
        Server testServer = new Server(serverSocket, logger);
        testServer.start();
    }

//    @AfterAll
//    public void tearDown() throws IOException {
//        testServer.stop();
//    }

    @Test
    void testServerIsReachableAfterStarting () throws Exception {
        InetAddress server = InetAddress.getByName("localhost");
        boolean reachable = server.isReachable(7000);
        assertTrue(reachable);
    }

//    @Test
//    void testServerAcceptsClientConnection () throws Exception {
//        Socket testClient = new Socket();
//        testClient.connect(new InetSocketAddress("localhost", 5000));
//        testServer.acceptClientConnection();
//        boolean connected = testClient.isConnected();
//        assertTrue(connected);
//        testClient.close();
//    }

//    @Test
//    void testLoggerIsLoggingAfterServerStarts () throws Exception {
//
//        File file = new File("~/com.olgaboiar.mint/artifact-1");
//        if (!file.exists()){
//            file = new File("testLogs.txt");
//        }
//        String readLastLineOfTestLogsFile = new FileLoggerTest().readLastLine(file);
//        String expected = "Connection on port " + port;
//        Assertions.assertEquals(expected, readLastLineOfTestLogsFile);
//    }
}
