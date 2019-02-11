package com.olgaboiar.mint;

import static com.olgaboiar.mint.Constants.BLANK_LINE;

public class Response {
    private Header header;
    private Body body;
    String responseToSend;

    public Response(Header header, Body body) {
        this.header = header;
        this.body = body;
    }

    public Header getHeader() {
        return header;
    }

    Body getBody() {
        return body;
    }

    String prepareResponse() {
        if (body.getBodyString().length() > 0) {
            responseToSend = header.prepareHeaders() + BLANK_LINE + body.getBodyString();
        } else {
            responseToSend = header.prepareHeaders();
        }
        return responseToSend;
    }
}
