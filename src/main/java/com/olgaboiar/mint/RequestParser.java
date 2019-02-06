package com.olgaboiar.mint;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RequestParser {
    Request parse(List<String> incomingRequest) throws MalformedURLException {
        String[] requestHeader = incomingRequest.get(0).split(" ");
        String method = requestHeader[0];
        String requestedFile = requestHeader[1];
        URL url = new URL("http", "0.0.0.0", 5000, requestedFile);
        Request parsedRequest = new Request(url, method);
        return parsedRequest;
    }
}
