package com.olgaboiar.mint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseGeneratorTest {
    @Test
    void testResponseGeneratorReturnsResponseObject() {
        ResponseGenerator testResponseGenerator = new ResponseGenerator();
        Response response = testResponseGenerator.generateResponse(Constants.Status.STATUS_CODE_200, "");
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 200 OK", "Content-Type: text/html", ""}, responseArray);
    }

    @Test
    void testResponseGeneratorReturnsResponseForNotFound() {
        ResponseGenerator testResponseGenerator = new ResponseGenerator();
        Response response = testResponseGenerator.generateResponse(Constants.Status.STATUS_CODE_404, "");
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 404 Not Found", "Content-Type: text/html", ""}, responseArray);
    }
}
