package com.olgaboiar.mint;

import java.net.URL;

public class Request {
    private String method;
    private URL url;
    private String body;

    public Request(URL url, String method) {
        this.url = url;
        this.method = method;
        this.body = "";

    }

    public String getMethod() {
        return method;
    }

    public String getUri() {
        return url.getPath();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
