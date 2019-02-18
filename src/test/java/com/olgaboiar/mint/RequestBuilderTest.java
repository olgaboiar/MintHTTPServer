package com.olgaboiar.mint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestBuilderTest {
    RequestBuilder testRequestBuilder;

    @BeforeEach
    public void init() throws IOException {
        MockServerConnection testSocket = new MockServerConnection();
        BufferedReaderWrapper in = new BufferedReaderWrapper(testSocket.listenToClientConnection(testSocket.acceptClientConnection()));
        testRequestBuilder = new RequestBuilder(new RequestParser(new Reader(in)));
    }

    @Test
    void testRequestBuilderCreatesRequestObject() throws IOException {
        List<String> incomingRequest = new ArrayList<String>();
        incomingRequest.add("HEAD /simple_get HTTP/1.1");
        incomingRequest.add("Accept: */*");
        incomingRequest.add("User-Agent: Ruby");
        incomingRequest.add("Connection: close");
        incomingRequest.add("Host: 0.0.0.0:5000");
        Request parsedRequest = testRequestBuilder.buildRequest(incomingRequest);

        assertTrue(parsedRequest instanceof Request);
    }
}