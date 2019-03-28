package com.olgaboiar.mint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class ResponseGeneratorTest {
    Request testRequest;

    @BeforeEach
    public void init() throws MalformedURLException {
        testRequest = new Request(new URL("http://0.0.0.0:5000/method_options"), "GET", "Content-Type: text/html");
    }

    @Test
    void testResponseGeneratorReturnsResponseObject() {
        ResponseGenerator testResponseGenerator = new ResponseGenerator();
        Response response = testResponseGenerator.generateResponse(testRequest, Constants.Status.STATUS_CODE_200, "");
        String[] responseArray = {response.getHeader().getStatusLine(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 200 OK", ""}, responseArray);
    }

    @Test
    void testResponseGeneratorReturnsResponseForNotFound() {
        ResponseGenerator testResponseGenerator = new ResponseGenerator();
        Response response = testResponseGenerator.generateResponse(testRequest, Constants.Status.STATUS_CODE_404, "");
        String[] responseArray = {response.getHeader().getStatusLine(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 404 Not Found", ""}, responseArray);
    }
}
