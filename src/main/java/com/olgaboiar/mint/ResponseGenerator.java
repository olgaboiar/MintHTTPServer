package com.olgaboiar.mint;

public class ResponseGenerator {

//    public String[] generateResponse() {
//        String statusLine = ("HTTP/1.0 200 OK");
//        String contentType = ("Content-Type: text/html");
//        String blankLine = ("");
//
//        String[] responseArray = new String[] {statusLine, contentType, blankLine};
//
//        return responseArray;
//    }

    public Response generateResponse() {
        String statusLine = ("HTTP/1.0 200 OK");
        String contentType = ("Content-Type: text/html");
//        String blankLine = ("");

        Response response = new Response(statusLine, contentType);

        return response;
    }
}
