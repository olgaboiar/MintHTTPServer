package com.olgaboiar.mint;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @Test
    void getMethodReturnsGetMethodForGetRequest() throws MalformedURLException {
        Request testRequest = new Request(new URL("http://0.0.0.0:5000/method_options"), "GET");
        String actual = testRequest.getMethod();

        assertEquals("GET", actual);
    }

    @Test
    void getMethodReturnsPostMethodForPostRequest() throws MalformedURLException {
        Request testRequest = new Request(new URL("http://0.0.0.0:5000/method_options"), "POST");
        String actual = testRequest.getMethod();

        assertEquals("POST", actual);
    }

    @Test
    void getUriReturnsCorrectPath() throws MalformedURLException {
        Request testRequest = new Request(new URL("http://0.0.0.0:5000/method_options"), "POST");
        String actual = testRequest.getUri();

        assertEquals("/method_options", actual);
    }
}
