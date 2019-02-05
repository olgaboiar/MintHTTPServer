package com.olgaboiar.mint;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RouterTest {
    MockRouteMap testMap = new MockRouteMap();
    Router router = new Router(testMap);
    Response response;

    @Test
    void returns200OKWhenLegalRouteIsRequested() throws IOException {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        testRequest.setRequestedFile("/method_options");
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
    void returns404NotFoundWhenNonExistentRouteIsRequested() throws IOException {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        testRequest.setRequestedFile("/no-directory");
        testRequest.setProtocol("HTTP");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 404 Not Found", "Content-Type: text/html", ""}, responseArray);
    }

    @Test
    void returns404NotFoundWhenNonExistentFileIsRequested() throws IOException {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        testRequest.setRequestedFile("/main.html");
        testRequest.setProtocol("HTTP");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 404 Not Found", "Content-Type: text/html", ""}, responseArray);
    }

    @Test
    void returns301MovedWhenRedirectedRouteIsRequested() throws IOException {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        testRequest.setRequestedFile("/redirect");
        testRequest.setProtocol("HTTP");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 301 Moved Permanently", "Content-Type: text/html", ""}, responseArray);
    }

    @Test
    void returns405MethodNotAllowedWhenUnsupportedMethodRequested() throws IOException {
        Request testRequest = new Request();
        testRequest.setMethod("POST");
        testRequest.setRequestedFile("/index.html");
        testRequest.setProtocol("HTTP");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 405 Method Not Allowed", "Content-Type: text/html", ""}, responseArray);
    }
}
