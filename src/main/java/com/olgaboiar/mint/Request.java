package com.olgaboiar.mint;

import java.net.URL;

public class Request {
    private String method;
    private URL url;

    public Request(URL url, String method) {
        this.url = url;
        this.method = method;

    }

    public String getMethod() {
        return method;
    }
//
    public String getUri() {
        return url.getPath();
    }
}
