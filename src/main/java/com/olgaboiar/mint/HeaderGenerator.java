package com.olgaboiar.mint;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.olgaboiar.mint.Constants.*;

public class HeaderGenerator {
    String currentDate;

    public Header generate(Request request, Status statusCode) {
        String statusLine = PROTOCOL + " " + statusCode.toString();
        currentDate = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());
        String date = "Date: " + currentDate;
        String contentType = "Content-Type: " + request.getContentType();
        Header header = new Header(statusLine, date, contentType);
        return header;
    }
}
