package com.olgaboiar.mint;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParser {
    Request parse(List<String> incomingRequest) throws MalformedURLException {
        String[] requestLine = incomingRequest.get(0).split(" ");
        Map<String, String> requestHeaders = parseRequestHeaders(incomingRequest);
        URL url = new URL(parseProtocol(requestLine), parseHost(requestHeaders), parsePort(requestHeaders), parsePath(requestLine));
        Request parsedRequest = new Request(url, parseMethod(requestLine));
        return parsedRequest;
    }

    private Map<String, String> parseRequestHeaders(List<String> incomingRequest) {
        List<String> headers = incomingRequest.subList(1, incomingRequest.size());
        Map<String, String> requestHeaders = new HashMap<String, String>();
        String[] restArr = new String[headers.size()];
        restArr = headers.toArray(restArr);
        for (int i = 0; i < headers.size(); i ++) {
            String[] line = restArr[i].split(": ");
            requestHeaders.put(line[0], line[1]);
        }
        return requestHeaders;
    }

    private Integer parsePort(Map<String, String> requestHeaders) {
        return Integer.parseInt(requestHeaders.get("Host").split(":")[1]);
    }

    private String parseHost(Map<String, String> requestHeaders) {
        return requestHeaders.get("Host").split(":")[0];
    }

    private String parseProtocol(String[] requestLine) {
        return requestLine[2].split("/")[0].toLowerCase();
    }

    private String parseMethod(String[] requestLine) {
        return requestLine[0];
    }

    private String parsePath(String[] requestLine) {
        return requestLine[1];
    }
}
