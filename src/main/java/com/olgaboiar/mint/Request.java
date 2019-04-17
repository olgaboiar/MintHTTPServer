package com.olgaboiar.mint;

import java.net.URL;

public class Request {
    private String method;
    private String contentType;
    private URL url;
    private String body;

    public Request(URL url, String method, String contentType) {
        this.url = url;
        this.method = method;
        this.contentType = contentType;
        this.body = "";

    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return url.getPath();
    }

    public String getContentType() {
        return contentType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
