package com.olgaboiar.mint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseGeneratorTest {
    @Test
    void testResponseGeneratorReturnsResponseObject() {
        ResponseGenerator testResponseGenerator = new ResponseGenerator();
        Response response = testResponseGenerator.generateResponse();
        String[] responseArray = {response.getStatusLine(), response.getContentType(), ""};
        assertArrayEquals(new String[]{"HTTP/1.0 200 OK", "Content-Type: text/html", ""}, responseArray);
    }

    @Test
    void testResponseGeneratorReturnsResponseForNotFound() {
        ResponseGenerator testResponseGenerator = new ResponseGenerator();
        Response response = testResponseGenerator.generateNotFoundResponse();
        String[] responseArray = {response.getStatusLine(), response.getContentType(), ""};
        assertArrayEquals(new String[]{"HTTP/1.0 404 Not Found", "Content-Type: text/plain", ""}, responseArray);
    }

}