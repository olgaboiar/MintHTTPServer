package com.olgaboiar.mint;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class RequestBuilder {
    RequestParser parser;

    public RequestBuilder(RequestParser parser) {
        this.parser = parser;
    }

    Request buildRequest(List<String> incomingRequest) throws IOException {
        String[] requestLine = parser.parseRequestLine(incomingRequest);
//        Map<String, String> requestHeaders = parser.parseRequestHeaders(incomingRequest);
        URL url = parser.createUrl(incomingRequest);
        Request parsedRequest = new Request(url, parser.parseMethod(requestLine));
        if (parser.requestBodyExists()) {
            parsedRequest.setBody(parser.parseBody());
        }
        return parsedRequest;
    }
}
