package com.olgaboiar.mint;

public class ResponseGenerator {
    HeaderGenerator headerGenerator = new HeaderGenerator();

    public Response generateResponse(String statusCode, String body) {
        String contentType = ("Content-Type: text/html");
        Header header = headerGenerator.generate(statusCode, contentType);
        Body responseBody = new Body(body);
        Response response = new Response(header, responseBody);
        return response;
    }
}
