package com.olgaboiar.mint;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServerTest {
    Server testServer;

    @BeforeAll
    public void setUp () throws Exception {
        String host = "localhost";
        int port = 5000;
        testServer = new Server(host, port);
        testServer.start();
    }

    @AfterAll
    public void tearDown() throws IOException {
        testServer.stop();
    }

    @Test
    void testServerIsReachableAfterStarting () throws Exception {
        InetAddress server = InetAddress.getByName("localhost");
        boolean reachable = server.isReachable(7000);
        assertTrue(reachable);
    }

    @Test
    void testServerAcceptsClientConnection () throws Exception {
        Socket testClient = new Socket();
        testClient.connect(new InetSocketAddress("localhost", 5000));
        testServer.acceptClientConnection();
        boolean connected = testClient.isConnected();
        assertTrue(connected);
        testClient.close();
    }

//    @Test
//    void testServerIsNotReachableAfterStopping () throws Exception {
//        testServer.stop();
//        InetAddress server = InetAddress.getByName("localhost");
//        boolean reachable = server.isReachable(5000);
//        assertFalse(reachable);
//    }

}

//    @Override
//    public void listenToClientConnection() throws IOException {
//        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//        out = new PrintWriter(clientSocket.getOutputStream());
//    }
//
//    @Override
//    public void closeClientConnection() throws IOException {
//        out.close();
//        in.close();
//        clientSocket.close();
//    }
//
//    @Override
//    public void stop() throws IOException {
//        serverSocket.close();
//    }