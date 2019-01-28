package com.olgaboiar.mint;

public class ResponseGenerator {
    String test_body = "test";
    HeaderGenerator headerGenerator = new HeaderGenerator();


    public Response generateResponse(String statusCode) {
        String contentType = ("Content-Type: text/html");
        Header header = headerGenerator.generate(statusCode, contentType);
        Body body = new Body(test_body);
        Response response = new Response(header, body);

        return response;
    }

}
