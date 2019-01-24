package com.olgaboiar.mint;

public class Request {
    private String method;
    private String requestedFile;

    String getMethod() {
        return method;
    }

    String getRequestedFile() {
        return requestedFile;
    }

    void setMethod(String method) {
        this.method = method;
    }

    void setRequestedFile(String requestedFile) {
        this.requestedFile = requestedFile;
    }
}
