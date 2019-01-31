package com.olgaboiar.mint.loggers;

import java.io.IOException;

public class ConsoleLogger implements ILogger {

    @Override
    public void logMessage(String message) throws IOException {
        System.out.println(message);
    }
}
