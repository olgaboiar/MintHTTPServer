package com.olgaboiar.mint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestBuilderTest {
    RequestBuilder testRequestBuilder;

    @BeforeEach
    public void init(){
        testRequestBuilder = new RequestBuilder(new RequestParser());
    }

    @Test
    void testRequestBuilderCreatesRequestObject() throws MalformedURLException {
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