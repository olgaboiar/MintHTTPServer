package com.olgaboiar.mint;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class RequestBuilder {
    RequestParser parser;
    Reader reader;

    public RequestBuilder(RequestParser parser, Reader reader) {
        this.parser = parser;
        this.reader = reader;
    }

    Request buildRequest(List<String> incomingRequest) throws IOException {
        String[] requestLine = incomingRequest.get(0).split(" ");
        Map<String, String> requestHeaders = parser.parseRequestHeaders(incomingRequest);
        URL url = new URL(
                parser.parseProtocol(requestLine),
                parser.parseHost(requestHeaders),
                parser.parsePort(requestHeaders),
                parser.parsePath(requestLine)
        );
        Request parsedRequest = new Request(url, parser.parseMethod(requestLine));
        if ((parser.contentLengthExist(requestHeaders)) && (Integer.parseInt(parser.parseContentLength(requestHeaders)))>0) {
            String body = reader.readClientInputBody(Integer.parseInt(parser.parseContentLength(requestHeaders)));
//            String newBody = body.trim();
//            String newBody = body.substring(0, body.length() - 1);
//            String newBody = body.replaceAll("[\n\r]", "");
            parsedRequest.setBody(body);
        }
        return parsedRequest;
    }
}
