package com.olgaboiar.mint;

public class Request {
    private String method;
    private String uri;
    private String protocol;

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getProtocol() {
        return protocol;
    }

    void setMethod(String method) {
        this.method = method;
    }

    void setRequestedFile(String uri) {
        this.uri = uri;
    }

    void setProtocol(String protocol) {
        this.protocol = protocol;
    }
}
