package com.olgaboiar.mint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseGeneratorTest {
    @Test
    void testResponseGeneratorReturnsResponseArray() {
        ResponseGenerator testResponseGenerator = new ResponseGenerator();
        String[] responseArray = testResponseGenerator.generateResponse();
        assertArrayEquals(new String[]{"HTTP/1.0 200 OK", "Content-Type: text/html", ""}, responseArray);
    }

}