package com.olgaboiar.mint;

public class Request {
    private String method;
    private String requestedFile;
    private String protocol;

    String getMethod() {
        return method;
    }

    String getRequestedFile() {
        return requestedFile;
    }

    String getProtocol() {
        return protocol;
    }

    void setMethod(String method) {
        this.method = method;
    }

    void setRequestedFile(String requestedFile) {
        this.requestedFile = requestedFile;
    }

    void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
