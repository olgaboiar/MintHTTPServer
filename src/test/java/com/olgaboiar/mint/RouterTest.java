package com.olgaboiar.mint;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class RouterTest {
    String serverTestRoutesPath = "/testRoutes.yaml";
    RoutesConfiguration serverTestRoutes = new RoutesConfiguration(serverTestRoutesPath);
    MockRouteMap testMap = new MockRouteMap(serverTestRoutes);
    Router router = new Router(testMap);
    Response response;

    RouterTest() throws IOException {
    }

    @Test
    void returns200OKWhenLegalRouteIsRequested() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/test");
        String method = "GET";
        Request testRequest = new Request(url, method, "Content-Type: text/html");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 200 OK", ""}, responseArray);
    }

    @Test
    void returns200OKWhenLegalRouteHeadIsRequested() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/test");
        String method = "HEAD";
        Request testRequest = new Request(url, method, "Content-Type: text/html");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 200 OK", ""}, responseArray);
    }

    @Test
    void returns200OKWhenExistentFileIsRequested() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/index.html");
        String method = "GET";
        Request testRequest = new Request(url, method, "Content-Type: text/html");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 200 OK", ""}, responseArray);
    }

    @Test
    void returns404NotFoundWhenNonExistentRouteIsRequested() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/dead-end");
        String method = "GET";
        Request testRequest = new Request(url, method, "Content-Type: text/html");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 404 Not Found", ""}, responseArray);
    }

    @Test
    void returns404NotFoundWhenNonExistentFileIsRequested() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/no.html");
        String method = "GET";
        Request testRequest = new Request(url, method, "Content-Type: text/html");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 404 Not Found", ""}, responseArray);
    }

    @Test
    void returns301MovedWhenRedirectedRouteIsRequested() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/test_redirect");
        String method = "GET";
        Request testRequest = new Request(url, method, "Content-Type: text/html");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 301 Moved Permanently", ""}, responseArray);
    }

    @Test
    void returns405MethodNotAllowedWhenUnsupportedMethodRequested() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/index.html");
        String method = "POST";
        Request testRequest = new Request(url, method, "Content-Type: text/html");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), ""};

        assertArrayEquals(new String[]{"HTTP/1.1 405 Method Not Allowed", ""}, responseArray);
    }

    @Test
    void returns200OKAndAllowedMethods() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/test");
        String method = "OPTIONS";
        Request testRequest = new Request(url, method, "Content-Type: text/html");
        response = router.route(testRequest);
        String[] responseArray = {response.getHeader().getStatusLine(), response.getHeader().createAllowHeader()};

        assertArrayEquals(new String[]{"HTTP/1.1 200 OK", "Allow: HEAD,POST,GET,PUT,OPTIONS"}, responseArray);
    }

    @Test
    void returns200OKWhenPostRequest() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/echo_body");
        String method = "POST";
        Request testRequest = new Request(url, method, "Content-Type: text/html");
        response = router.route(testRequest);
        String responseStatusHeader = response.getHeader().getStatusLine();

        assertEquals("HTTP/1.1 200 OK", responseStatusHeader);
    }

    @Test
    void returnResponseWithBodyEqualToRequestBody() throws IOException {
        URL url = new URL("http://0.0.0.0:5000/echo_body");
        String method = "POST";
        Request testRequest = new Request(url, method, "Content-Type: text/html");
        testRequest.setBody("test");
        response = router.route(testRequest);
        String responseBody = response.getBody().getBody();
//
        assertEquals("test", responseBody);
    }
}
