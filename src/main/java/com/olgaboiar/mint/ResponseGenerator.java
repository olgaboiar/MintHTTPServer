package com.olgaboiar.mint;

public class ResponseGenerator {
//    String test_body = "test";
    HeaderGenerator headerGenerator = new HeaderGenerator();
    String body;


    public Response generateResponse(String statusCode, String body) {
        String contentType = ("Content-Type: text/html");
        Header header = headerGenerator.generate(statusCode, contentType);
        Body responseBody = new Body(body);
        Response response = new Response(header, responseBody);

        return response;
    }

}
