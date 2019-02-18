package com.olgaboiar.mint;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReaderTest {

    @Test
    void testGetInputHeadersForRequestWithHeadersOnly() throws IOException {
        List<String> expected = new ArrayList<String>();
        String requestLine = "GET /simple_get HTTP/1.1";
        expected.add(requestLine);
        StringReader stringReader = new StringReader(requestLine);
        BufferedReader testBufferReader = new BufferedReader(stringReader);
        Reader testReader = new Reader(testBufferReader);
        List<String> testClientInput = testReader.readInput();

        assertEquals(expected, testClientInput);
    }

    @Test
    void testGetInputHeadersForRequestWithHeadersAndBody() throws IOException {
        List<String> expected = new ArrayList<String>();
        String requestLine = "GET /simple_get HTTP/1.1\n\nsome_body";
        String requestHeader = "GET /simple_get HTTP/1.1";
        expected.add(requestHeader);
        StringReader stringReader = new StringReader(requestLine);
        BufferedReader testBufferReader = new BufferedReader(stringReader);
        Reader testReader = new Reader(testBufferReader);
        List<String> testClientInput = testReader.readInput();

        assertEquals(expected, testClientInput);
    }

    @Test
    void testGetInputBodyForRequestWithHeadersAndBody() throws IOException {
        String expected = "some_body";
        String requestLine = "GET /simple_get HTTP/1.1\nContent-Length: 9\n\nsome_body";
        StringReader stringReader = new StringReader(requestLine);
        BufferedReader testBufferReader = new BufferedReader(stringReader);
        Reader testReader = new Reader(testBufferReader);
        List<String> testClientInput = testReader.readInput();
        MockServerConnection testSocket = new MockServerConnection();
        BufferedReader in = testSocket.listenToClientConnection(testSocket.acceptClientConnection());
        RequestParser requestParser = new RequestParser(new Reader(in));
        Map<String, String> requestHeaders = requestParser.parseRequestHeaders(testClientInput);
        int contentLength = Integer.valueOf(requestParser.parseContentLength(requestHeaders));
        String testClientInputBody = testReader.readChars(contentLength);

        assertEquals(expected, testClientInputBody);
    }
}
