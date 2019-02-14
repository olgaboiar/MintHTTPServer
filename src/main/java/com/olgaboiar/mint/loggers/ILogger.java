package com.olgaboiar.mint.loggers;

import java.io.IOException;

public interface ILogger {
    void logMessage(String message) throws IOException;
}
