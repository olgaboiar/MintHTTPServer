package com.olgaboiar.mint;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class RouterTest {
    MockRouteMap testMap = new MockRouteMap();
    Router router = new Router(testMap);
    Response response;

    @Test
    void returns200OKWhenLegalRouteIsRequested() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/method_options");
        String method = "GET";
        Request testRequest = new Request(url, method);
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 200 OK", "Content-Type: text/html", ""}, responseArray);
    }

    @Test
    void returns200OKWhenExistentFileIsRequested() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/index.html");
        String method = "GET";
        Request testRequest = new Request(url, method);
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 200 OK", "Content-Type: text/html", ""}, responseArray);
    }

    @Test
    void returns404NotFoundWhenNonExistentRouteIsRequested() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/dead-end");
        String method = "GET";
        Request testRequest = new Request(url, method);
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 404 Not Found", "Content-Type: text/html", ""}, responseArray);
    }

    @Test
    void returns404NotFoundWhenNonExistentFileIsRequested() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/no.html");
        String method = "GET";
        Request testRequest = new Request(url, method);
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 404 Not Found", "Content-Type: text/html", ""}, responseArray);
    }

    @Test
    void returns301MovedWhenRedirectedRouteIsRequested() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/redirect");
        String method = "GET";
        Request testRequest = new Request(url, method);
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 301 Moved Permanently", "Content-Type: text/html", ""}, responseArray);
    }

    @Test
    void returns405MethodNotAllowedWhenUnsupportedMethodRequested() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/index.html");
        String method = "POST";
        Request testRequest = new Request(url, method);
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().getContentType(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 405 Method Not Allowed", "Content-Type: text/html", ""}, responseArray);
    }

    @Test
    void returns200OKAndAllowedMethods() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/method_options");
        String method = "OPTIONS";
        Request testRequest = new Request(url, method);
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().createAllowHeader()};

        assertArrayEquals(new String[]{"HTTP/1.1 200 OK", "Allow: HEAD, POST, GET, OPTIONS, PUT"}, responseArray);
    }
}
