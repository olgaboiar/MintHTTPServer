package com.olgaboiar.mint;

import java.util.List;

public class RequestParser {
    Request parse(List<String> incomingRequest) {
        String[] requestHeader = incomingRequest.get(0).split(" ");
        String method = requestHeader[0];
        String requestedFile = requestHeader[1];
        String protocol = requestHeader[2];
        Request parsedRequest = new Request();
        parsedRequest.setMethod(method);
        parsedRequest.setRequestedFile(requestedFile);
        parsedRequest.setProtocol(protocol);
        return parsedRequest;
    }
}
