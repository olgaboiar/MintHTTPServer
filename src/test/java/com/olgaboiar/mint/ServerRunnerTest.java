package com.olgaboiar.mint;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.InetAddress;

import static org.junit.jupiter.api.Assertions.*;

class ServerRunnerTest {
    ServerProcess serverProcess;

    @BeforeEach
    public void setUp () throws Exception {
        String build = "build/libs/com.olgaboiar.mint-1.0-SNAPSHOT.jar";
        serverProcess = ServerProcess.start(build);
    }

    @AfterEach
    public void tearDown() {
        serverProcess.stop();
    }

    @Test
    void testServerIsReachable () throws Exception {
        InetAddress server = InetAddress.getByName("localhost");
        boolean reachable = server.isReachable(5000);
        assertTrue(reachable);
    }

}