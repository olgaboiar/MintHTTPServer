package com.olgaboiar.mint;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.olgaboiar.mint.Constants.*;

public class HeaderGenerator {
    String currentDate;

    public Header generate(Status statusCode, String contentType) {
        String statusLine = PROTOCOL + " " + statusCode.toString();
        currentDate = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());
        String date = "Date: " + currentDate;
        Header header = new Header(statusLine, contentType, date);
        return header;
    }
}
