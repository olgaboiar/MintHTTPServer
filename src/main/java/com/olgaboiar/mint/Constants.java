package com.olgaboiar.mint;

import com.olgaboiar.mint.loggers.FileLogger;
import com.olgaboiar.mint.loggers.ILogger;

public final class Constants {

    public static final int DEFAULT_PORT = 5000;
    public static final String DEFAULT_HOST = "localhost";
    public static final ILogger DEFAULT_LOGGER = new FileLogger("logs.txt");

//    public static final String STATUS_CODE_200 = "200 OK";
//    public static final String STATUS_CODE_301 = "301 Moved Permanently";
//    public static final String STATUS_CODE_404 = "404 Not Found";
//    public static final String STATUS_CODE_405 = "405 Method Not Allowed";

    public static final String PROTOCOL = "HTTP/1.1";

    public enum Status {
        STATUS_CODE_200("200 OK"),
        STATUS_CODE_301("301 Moved Permanently"),
        STATUS_CODE_404("404 Not Found"),
        STATUS_CODE_405("405 Method Not Allowed");

        final String status;

        Status(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return status;
        }
    }
}
