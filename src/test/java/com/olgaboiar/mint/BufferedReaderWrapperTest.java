package com.olgaboiar.mint;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class BufferedReaderWrapperTest {

    @Test
    void readLine() throws IOException {
        String requestLine = "GET /simple_get HTTP/1.1";
        StringReader stringReader = new StringReader(requestLine);
        BufferedReader testIn = new BufferedReader(stringReader);
        BufferedReaderWrapper testBufferedReaderWrapper = new BufferedReaderWrapper(testIn);
        String actual = testBufferedReaderWrapper.readLine();

        assertEquals(requestLine, actual);
    }
}
