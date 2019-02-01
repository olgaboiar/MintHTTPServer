package com.olgaboiar.mint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @Test
    void getMethodReturnsNullWhenNoMethodSet() {
        Request testRequest = new Request();
        String method = testRequest.getMethod();
        assertNull(method);
    }

    @Test
    void getRequestedFileReturnsNullWhenNoMethodSet() {
        Request testRequest = new Request();
        String file = testRequest.getRequestedFile();
        assertNull(file);
    }

    @Test
    void getprotocolReturnsNullWhenNoMethodSet() {
        Request testRequest = new Request();
        String protocol = testRequest.getProtocol();
        assertNull(protocol);
    }

    @Test
    void getMethodReturnsMethodWhenMethod() {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        String method = testRequest.getMethod();
        assertNotNull(method);
    }

    @Test
    void getRequestedFileReturnsFiledWhenFileIsSet() {
        Request testRequest = new Request();
        testRequest.setRequestedFile("/index.html");
        String file = testRequest.getRequestedFile();
        assertNotNull(file);
    }

    @Test
    void getProtocolReturnsProtocolWhenProtocolIsSet() {
        Request testRequest = new Request();
        testRequest.setProtocol("HTTP");
        String protocol = testRequest.getProtocol();
        assertNotNull(protocol);
    }

    @Test
    void setMethodSetsRequestMethodToGet() {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        String method = testRequest.getMethod();
        assertEquals("GET", method);
    }
}
