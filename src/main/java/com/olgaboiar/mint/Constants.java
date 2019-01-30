package com.olgaboiar.mint;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class Constants {

    public static final int DEFAULT_PORT = 5000;
    public static final String DEFAULT_HOST = "localhost";

    public static final String STATUS_CODE_200 = "200 OK";
    public static final String STATUS_CODE_404 = "404 Not Found";

    public static final String PROTOCOL = "HTTP/1.1";

    public static final String DATE = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());
}
