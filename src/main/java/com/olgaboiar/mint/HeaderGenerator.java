package com.olgaboiar.mint;

import static com.olgaboiar.mint.Constants.*;

public class HeaderGenerator {


    public Header generate(String statusCode, String contentType) {
        String statusLine = PROTOCOL + " " + statusCode;
        String date = "Date: " + DATE;
        Header header = new Header(statusLine, contentType, date);
        return header;
    }
}
