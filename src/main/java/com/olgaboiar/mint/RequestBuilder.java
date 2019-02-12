package com.olgaboiar.mint;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class RequestBuilder {
    RequestParser parser;

    public RequestBuilder(RequestParser parser) {
        this.parser = parser;
    }

    Request buildRequest(List<String> incomingRequest) throws MalformedURLException {
        String[] requestLine = incomingRequest.get(0).split(" ");
        Map<String, String> requestHeaders = parser.parseRequestHeaders(incomingRequest);
        URL url = new URL(
                parser.parseProtocol(requestLine),
                parser.parseHost(requestHeaders),
                parser.parsePort(requestHeaders),
                parser.parsePath(requestLine)
        );
        Request parsedRequest = new Request(url, parser.parseMethod(requestLine));
        return parsedRequest;
    }
}
