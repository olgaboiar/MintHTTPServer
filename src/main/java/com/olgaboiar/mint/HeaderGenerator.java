package com.olgaboiar.mint;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.olgaboiar.mint.Constants.*;

public class HeaderGenerator {
    String currentDate = DateTimeFormatter.RFC_1123_DATE_TIME.format(ZonedDateTime.now());

    public Header generate(String statusCode, String contentType) {
        String statusLine = PROTOCOL + " " + statusCode;
        String date = "Date: " + currentDate;
        Header header = new Header(statusLine, contentType, date);
        return header;
    }
}
