package com.olgaboiar.mint;

public class Header {
    private String statusLine;
    private String contentType;
    private String blankLine;
    private String date;

    public Header(String statusLine, String contentType, String date) {
        this.statusLine = statusLine;
        this.contentType = contentType;
        this.date = date;
        this.blankLine = ("");
    }

    String getStatusLine() {
        return statusLine;
    }

    String getContentType() {
        return contentType;
    }

    String getDate() {
        return date;
    }

    String prepareHeaders() {
        String headersToSend = statusLine + "\n"
                + contentType + "\n"
                + date + "\n"
                + blankLine;
        return headersToSend;
    }
}
