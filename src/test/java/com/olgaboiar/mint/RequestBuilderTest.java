package com.olgaboiar.mint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestBuilderTest {
    RequestBuilder testRequestBuilder;
    List<String> incomingRequest;

    @BeforeEach
    public void init() {
        String requestLine = "GET /simple_get HTTP/1.1\n\nsome_body";
        Reader testReader = new Reader(new MockBufferReader(requestLine));
        testRequestBuilder = new RequestBuilder(new RequestParser(testReader));
        incomingRequest = new ArrayList<>();
        incomingRequest.add("HEAD /simple_get HTTP/1.1");
        incomingRequest.add("Accept: */*");
        incomingRequest.add("User-Agent: Ruby");
        incomingRequest.add("Connection: close");
        incomingRequest.add("Host: 0.0.0.0:5000");
    }

    @Test
    void testRequestBuilderCreatesRequestObject() throws IOException {
        Request parsedRequest = testRequestBuilder.buildRequest(incomingRequest);

        assertTrue(parsedRequest instanceof Request);
    }

    @Test
    void testRequestBuilderCreatesRequestWithCorrectMethod() throws IOException {
        Request parsedRequest = testRequestBuilder.buildRequest(incomingRequest);
        String actualMethod = parsedRequest.getMethod();
        String expectedMethod = "HEAD";

        assertEquals(expectedMethod, actualMethod);
    }

    @Test
    void testRequestBuilderCreatesRequestWithCorrectUri() throws IOException {
        Request parsedRequest = testRequestBuilder.buildRequest(incomingRequest);
        String actualUri = parsedRequest.getUri();
        String expectedUri = "/simple_get";

        assertEquals(expectedUri, actualUri);
    }

}