package com.olgaboiar.mint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RouterTest {
    Router router = new Router();
    Response response;

    @Test
    void returns200OKWhenLegalRouteIsRequested() {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        testRequest.setRequestedFile("/index.html");
        testRequest.setProtocol("HTTP");
        response = router.route(testRequest);
        String[] responseArray = {response.getStatusLine(), response.getContentType(), ""};
        assertArrayEquals(new String[]{"HTTP/1.0 200 OK", "Content-Type: text/html", ""}, responseArray);
    }

    @Test
    void returnsNotFoundWhenNonExistentRouteIsRequested() {
        Request testRequest = new Request();
        testRequest.setMethod("GET");
        testRequest.setRequestedFile("/no-directory");
        testRequest.setProtocol("HTTP");
        response = router.route(testRequest);
        String[] responseArray = {response.getStatusLine(), response.getContentType(), ""};
        assertArrayEquals(new String[]{"HTTP/1.0 404 Not Found", "Content-Type: text/plain", ""}, responseArray);
    }

}