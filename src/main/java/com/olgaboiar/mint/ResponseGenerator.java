package com.olgaboiar.mint;

public class ResponseGenerator {
    static Body responseBody;

    public static Response generateResponse(Request request, Constants.Status status, String...body) {
        HeaderGenerator headerGenerator = new HeaderGenerator();
        Header header = headerGenerator.generate(request, status);
        if (body.length > 0) {
            responseBody = new Body(body[0]);
        } else {
            responseBody = new Body("");
        }
        Response response = new Response(header, responseBody);
        return response;
    }
}
