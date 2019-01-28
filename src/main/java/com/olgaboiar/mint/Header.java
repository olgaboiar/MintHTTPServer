package com.olgaboiar.mint;

public class Header {
    private String statusLine;
    private String contentType;
    private String blankLine;

    public Header(String statusLine, String contentType) {
        this.statusLine = statusLine;
        this.contentType = contentType;
        this.blankLine = ("");
    }

    String getStatusLine() {
        return statusLine;
    }

    String getContentType() {
        return contentType;
    }
}
