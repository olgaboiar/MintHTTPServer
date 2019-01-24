package com.olgaboiar.mint;

import java.util.List;

public class RequestParser {
    Request parse(List<String> incomingRequest) {
        String[] requestLineArr = incomingRequest.get(0).split(" ");
        String method = requestLineArr[0];
        String requestedFile = requestLineArr[1];
        Request parsedRequest = new Request();
        parsedRequest.setMethod(method);
        parsedRequest.setRequestedFile(requestedFile);
        return parsedRequest;
    }

}
