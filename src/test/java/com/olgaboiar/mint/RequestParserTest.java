package com.olgaboiar.mint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RequestParserTest {
    RequestParser testRequestParser;
    Map<String, String> requestHeaders;
    String[] requestLine;

    @BeforeEach
    public void init() throws IOException {
        String request = "HEAD /test HTTP/1.1\nHost: 0.0.0.0:5000\n\nsome_body";
        StringReader stringReader = new StringReader(request);
        BufferedReader bufferedReader = new BufferedReader(stringReader);
        BufferedReaderWrapper testBufferedReader = new BufferedReaderWrapper(bufferedReader);
        Reader testReader = new Reader(testBufferedReader);
        List<String> incomingRequest = testReader.readInput();
        testRequestParser = new RequestParser(new Reader(testBufferedReader));
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

        assertEquals("/test", parsedPath);
    }
}
