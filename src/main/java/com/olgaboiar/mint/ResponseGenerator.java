package com.olgaboiar.mint;

public class ResponseGenerator {

    public static Response generateResponse(Constants.Status status, String body) {
        HeaderGenerator headerGenerator = new HeaderGenerator();
        String contentType = ("Content-Type: text/html");
        Header header = headerGenerator.generate(status, contentType);
        Body responseBody = new Body(body);
        Response response = new Response(header, responseBody);
        return response;
    }
}
