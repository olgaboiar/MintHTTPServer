package com.olgaboiar.mint;

import java.util.Set;

public class Header {
    private String statusLine;
    private String contentType;
    private String blankLine;
    private String date;
    String headersToSend;
    Set<String> allowedMethods;

    public Header(String statusLine, String contentType, String date) {
        this.statusLine = statusLine;
        this.contentType = contentType;
        this.date = date;
        this.blankLine = ("");
        this.allowedMethods = null;
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
        if (allowedMethods != null) {
            String methods = String.join(",", allowedMethods);
            String accessControl = "Allow: " + methods;
            headersToSend = statusLine + "\n"
                    + contentType + "\n"
                    + date + "\n"
                    + accessControl + "\n"
                    + blankLine;
        } else {
            headersToSend = statusLine + "\n"
                + contentType + "\n"
                + date + "\n"
                + blankLine;
        }
        return headersToSend;
    }

    public void setAllowMethods(Set<String> allowedMethods1) {
        allowedMethods = allowedMethods1;
    }
}
