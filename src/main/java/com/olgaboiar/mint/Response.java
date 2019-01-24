package com.olgaboiar.mint;

public class Response {

    private String statusLine;
    private String contentType;
    private String blankLine;

    public Response(String statusLine, String contentType) {
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
