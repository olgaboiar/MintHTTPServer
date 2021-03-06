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
    public Body getBody() {
        return body;
    }

    String prepareResponse() {
        if (body.getBody().length() > 0) {
            responseToSend = header.prepareHeaders() + BLANK_LINE + body.getBody();
        } else {
            responseToSend = header.prepareHeaders() + BLANK_LINE;
        }
        return responseToSend;
    }
}
