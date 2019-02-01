package com.olgaboiar.mint;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RouterTest {
    Router router = new Router();
    Response response;

    @Test
    void returns200OKWhenLegalRouteIsRequested() throws IOException {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        testRequest.setRequestedFile("/simple_get");
        testRequest.setProtocol("HTTP");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 200 OK", "Content-Type: text/html", ""}, responseArray);
    }

    @Test
    void returns200OKWhenExistentFileIsRequested() throws IOException {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        testRequest.setRequestedFile("/index.html");
        testRequest.setProtocol("HTTP");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 200 OK", "Content-Type: text/html", ""}, responseArray);
    }

    @Test
    void returnsNotFoundWhenNonExistentRouteIsRequested() throws IOException {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        testRequest.setRequestedFile("/no-directory");
        testRequest.setProtocol("HTTP");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 404 Not Found", "Content-Type: text/html", ""}, responseArray);
    }
}
