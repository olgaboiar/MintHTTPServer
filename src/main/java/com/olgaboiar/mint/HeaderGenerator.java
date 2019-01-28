package com.olgaboiar.mint;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class HeaderGenerator {
    private static final String PROTOCOL = "HTTP/1.1";
    private final String DATE = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());

    public Header generate(String statusCode, String contentType) {
        String statusLine = PROTOCOL + " " + statusCode;
        String date = "Date: " + DATE;
        Header header = new Header(statusLine, contentType, date);
        return header;
    }
}
