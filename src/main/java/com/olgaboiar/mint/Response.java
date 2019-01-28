package com.olgaboiar.mint;

public class Response {
    private Header header;
    private Body body;

    public Response(Header header, Body body) {
        this.header = header;
        this.body = body;
    }

    Header getHeader() {
        return header;
    }

}
