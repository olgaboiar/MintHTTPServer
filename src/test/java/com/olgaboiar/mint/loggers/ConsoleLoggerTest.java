package com.olgaboiar.mint.loggers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

class ConsoleLoggerTest {

    @Test
    void getMethodReturnsNullWhenNoMethodSet() throws IOException {
        ConsoleLogger testConsoleLogger = new ConsoleLogger();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String message = "test log message";
        testConsoleLogger.logMessage(message);

        Assertions.assertEquals(message + "\n", outContent.toString());
    }

}
