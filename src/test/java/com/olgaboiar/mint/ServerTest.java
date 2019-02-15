package com.olgaboiar.mint;

import com.olgaboiar.mint.loggers.ILogger;
import com.olgaboiar.mint.loggers.MockFileLogger;
import org.junit.jupiter.api.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ServerTest {
    String serverTestRoutesPath = "src/test/java/com/olgaboiar/mint/testRoutes.yaml";
    ILogger logger = new MockFileLogger();

    @BeforeAll
    public void setUp () throws Exception {
        MockServerConnection serverSocket = new MockServerConnection();
        Server testServer = new Server(serverSocket, logger, serverTestRoutesPath);
        testServer.start();
    }
}
