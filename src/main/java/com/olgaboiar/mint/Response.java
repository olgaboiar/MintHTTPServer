package com.olgaboiar.mint;

public class Response {

//    private String statusLine;
//    private String contentType;
//    private String blankLine;
    private Header header;
    private Body body;

    public Response(Header header, Body body) {
//        this.statusLine = statusLine;
//        this.contentType = contentType;
//        this.blankLine = ("");
        this.header = header;
        this.body = body;
    }

    Header getHeader() {
        return header;
    }
//
//    String getContentType() {
//        return contentType;
//    }

}
