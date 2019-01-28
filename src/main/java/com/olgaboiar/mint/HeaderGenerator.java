package com.olgaboiar.mint;

public class HeaderGenerator {
    private static final String PROTOCOL = "HTTP/1.1";
    public Header generate(String statusCode, String contentType) {
        String statusLine = PROTOCOL + " " + statusCode;
        Header header = new Header(statusLine, contentType);
        return header;
    }
}
