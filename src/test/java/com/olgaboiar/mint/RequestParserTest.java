package com.olgaboiar.mint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestParserTest {
    RequestParser testRequestParser;
    List<String> incomingRequest;
    Map<String, String> requestHeaders;
    String[] requestLine;

    @BeforeEach
    public void init(){
        testRequestParser = new RequestParser();
        incomingRequest = new ArrayList<String>();
        incomingRequest.add("HEAD /simple_get HTTP/1.1");
        incomingRequest.add("Accept: */*");
        incomingRequest.add("User-Agent: Ruby");
        incomingRequest.add("Connection: close");
        incomingRequest.add("Host: 0.0.0.0:5000");
        requestHeaders = testRequestParser.parseRequestHeaders(incomingRequest);
        requestLine = incomingRequest.get(0).split(" ");
    }

    @Test
    void testRequestParserParsesHost() {
        String parsedHost = testRequestParser.parseHost(requestHeaders);

        assertEquals("0.0.0.0", parsedHost);
    }

    @Test
    void testRequestParserParsesPort() {
        int parsedPort = testRequestParser.parsePort(requestHeaders);

        assertEquals(5000, parsedPort);
    }

    @Test
    void testRequestParserParsesProtocol() {
        String parsedProtocol = testRequestParser.parseProtocol(requestLine);

        assertEquals("http", parsedProtocol);
    }

    @Test
    void testRequestParserParsesMethod() {
        String parsedMethod = testRequestParser.parseMethod(requestLine);

        assertEquals("HEAD", parsedMethod);
    }

    @Test
    void testRequestParserParsesPath() {
        String parsedPath = testRequestParser.parsePath(requestLine);

        assertEquals("/simple_get", parsedPath);
    }
}
