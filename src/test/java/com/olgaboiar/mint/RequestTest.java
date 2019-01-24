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
    void getMethodReturnsMethodWhenMethod() {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        String method = testRequest.getMethod();
        assertNotNull(method);
    }

    @Test
    void setMethodSetsRequestMethodToGet() {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        String method = testRequest.getMethod();
        assertEquals("GET", method);
    }
}