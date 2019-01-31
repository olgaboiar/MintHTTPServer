package com.olgaboiar.mint;

import com.olgaboiar.mint.loggers.FileLogger;
import com.olgaboiar.mint.loggers.ILogger;

public final class Constants {

    public static final int DEFAULT_PORT = 5000;
    public static final String DEFAULT_HOST = "localhost";
    public static final ILogger DEFAULT_LOGGER = new FileLogger("logs.txt");

    public static final String STATUS_CODE_200 = "200 OK";
    public static final String STATUS_CODE_404 = "404 Not Found";

    public static final String PROTOCOL = "HTTP/1.1";
}
