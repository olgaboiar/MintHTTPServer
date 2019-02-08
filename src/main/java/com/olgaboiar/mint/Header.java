package com.olgaboiar.mint;

import java.util.ArrayList;

import static com.olgaboiar.mint.Constants.BLANK_LINE;

public class Header {
    private String statusLine;
    private String contentType;
    private String date;
    ArrayList<String> allowedMethods;
    String redirect;

    public Header(String statusLine, String contentType, String date) {
        this.statusLine = statusLine;
        this.contentType = contentType;
        this.date = date;
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
        ArrayList<String> headers = new ArrayList<String>();
        headers.add(getStatusLine());
        headers.add(getContentType());
        headers.add(getDate());
        if (allowedMethodsExist()) {
            String aloowHeader = createAllowHeader();
            headers.add(aloowHeader);
        }
        if (redirectExist()) {
            String redirectHeader = createRedirectHeader();
            headers.add(redirectHeader);
        }
        return headers;
    }

    public String prepareHeaders() {
        String headersToSend = String.join("\n", getHeaders());
        return headersToSend;
    }

    public boolean allowedMethodsExist () {
        if (allowedMethods != null) {
            return true;
        }
        return false;
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

    public boolean redirectExist () {
        if (redirect != null) {
            return true;
        }
        return false;
    }

    public void setAllowMethods(ArrayList<String> methods) {
        allowedMethods = methods;
    }

    public void setRedirection(String redirectTarget) {
        redirect = redirectTarget;
    }
}
