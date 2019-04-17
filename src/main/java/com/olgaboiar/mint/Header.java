package com.olgaboiar.mint;

import java.util.ArrayList;

public class Header {
    private String statusLine;
    private String date;
    String contentType;
    ArrayList<String> allowedMethods;
    String redirect;

    public Header(String statusLine, String date) {
        this.statusLine = statusLine;
        this.date = date;
        this.contentType = null;
        this.allowedMethods = null;
        this.redirect = null;
    }

    String getStatusLine() {
        return statusLine;
    }

    String getDate() {
        return date;
    }

    String getContentType() {
        return contentType;
    }

    public ArrayList<String> getHeaders() {
        ArrayList<String> headers = new ArrayList<String>();
        headers.add(getStatusLine());
        headers.add(getDate());
        if (contentTypeExist()) {
            String contentTypeHeader = createContentTypeHeader();
            headers.add(contentTypeHeader);
        }
        if (allowedMethodsExist()) {
            String allowHeader = createAllowHeader();
            headers.add(allowHeader);
        }
        if (redirectExist()) {
            String redirectHeader = createRedirectHeader();
            headers.add(redirectHeader);
        }
        return headers;
    }

    public String prepareHeaders() {
        String headersToSend = String.join("\r\n", getHeaders());
        return headersToSend;
    }

    public boolean allowedMethodsExist () {
        return allowedMethods != null;
    }

    public boolean contentTypeExist () {
        return contentType != null;
    }

    public String createAllowHeader() {
        String allMethods = String.join(",", allowedMethods);
        String allowHeader = "Allow: " + allMethods;
        return allowHeader;

    }

    public String createRedirectHeader () {
        String redirectHeader = "Location: " + redirect;
        return redirectHeader;
    }

    public String createContentTypeHeader () {
        String contentTypeHeader = "Content-Type: " + contentType;
        return contentTypeHeader;
    }

    public boolean redirectExist () {
        return redirect != null;
    }

    public void setAllowMethods(ArrayList<String> methods) {
        allowedMethods = methods;
    }

    public void setRedirection(String redirectTarget) {
        redirect = redirectTarget;
    }

    public void setContentType(String fileContentType) {
        contentType = fileContentType;
    }
}
