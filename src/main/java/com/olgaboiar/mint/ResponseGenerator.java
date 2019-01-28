package com.olgaboiar.mint;

public class ResponseGenerator {
    String test_body = "test";

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
        Header header = new Header(statusLine, contentType);
        Body body = new Body(test_body);
        Response response = new Response(header, body);

        return response;
    }

    public Response generateNotFoundResponse() {
        String statusLine = ("HTTP/1.0 404 Not Found");
        String contentType = ("Content-Type: text/plain");

        Header header = new Header(statusLine, contentType);
        Body body = new Body(test_body);
        Response response = new Response(header, body);

        return response;
    }
}
