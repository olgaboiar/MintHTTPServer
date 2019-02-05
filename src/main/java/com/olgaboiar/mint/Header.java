package com.olgaboiar.mint;

import java.util.ArrayList;
import java.util.Set;

public class Header {
    private String statusLine;
    private String contentType;
    private String blankLine;
    private String date;
    ArrayList<String> headers;
    String[] allowedMethods;
    String redirect;

    public Header(String statusLine, String contentType, String date) {
        this.statusLine = statusLine;
        this.contentType = contentType;
        this.date = date;
        this.blankLine = ("");
        this.allowedMethods = null;
        this.redirect = null;
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

    public ArrayList<String> getHeaders() {
        headers = new ArrayList<String>();
        headers.add(getStatusLine());
        headers.add(getContentType());
        headers.add(getDate());
        if (allowedMethods != null) {
            String allMethods = String.join(",", allowedMethods);
            String accessControl = "Allow: " + allMethods;
            headers.add(accessControl);
        }
        if (redirect != null) {
            String redirectHeader = "Location: " + redirect;
            headers.add(redirectHeader);
        }
        return headers;
    }

    String prepareHeaders(ArrayList<String> headers) {
        String headersToSend = String.join("\n", headers);
        headersToSend += blankLine;
        return headersToSend;
    }

    public void setAllowMethods(String[] methods) {
        allowedMethods = methods;
    }

    public void setRedirection(String redirectTarget) {
        redirect = redirectTarget;
    }
}
