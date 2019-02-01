package com.olgaboiar.mint;

public class Response {
    private Header header;
    private Body body;
    String responseToSend;

    public Response(Header header, Body body) {
        this.header = header;
        this.body = body;
    }

    Header getHeader() {
        return header;
    }

    Body getBody() {
        return body;
    }

    String prepareResponse() {
        if (body.getBodyString().length() > 0) {
            responseToSend = header.prepareHeaders() + "\n" + body.getBodyString();
        } else {
            responseToSend = header.prepareHeaders();
        }
        return responseToSend;
    }
}
