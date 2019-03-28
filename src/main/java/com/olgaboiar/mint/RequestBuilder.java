package com.olgaboiar.mint;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class RequestBuilder {
    RequestParser parser;

    public RequestBuilder(RequestParser parser) {
        this.parser = parser;
    }

    Request buildRequest(List<String> incomingRequest) throws IOException {
        String[] requestLine = parser.parseRequestLine(incomingRequest);
        URL url = parser.createUrl(incomingRequest);
        String contentType = parser.parseContentType(incomingRequest);
        Request parsedRequest = new Request(url, parser.parseMethod(requestLine), contentType);
        if (parser.requestBodyExists()) {
            parsedRequest.setBody(parser.parseBody());
        }
        return parsedRequest;
    }
}
