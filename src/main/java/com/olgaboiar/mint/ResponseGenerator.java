package com.olgaboiar.mint;

public class ResponseGenerator {
    String test_body = "test";
    HeaderGenerator headerGenerator = new HeaderGenerator();


    public Response generateResponse() {
        String statusCode = ("200 OK");
        String contentType = ("Content-Type: text/html");
        Header header = headerGenerator.generate(statusCode, contentType);
        Body body = new Body(test_body);
        Response response = new Response(header, body);

        return response;
    }

    public Response generateNotFoundResponse() {
        String statusCode = ("Not Found");
        String contentType = ("Content-Type: text/plain");
        Header header = headerGenerator.generate(statusCode, contentType);
        Body body = new Body(test_body);
        Response response = new Response(header, body);

        return response;
    }
}
