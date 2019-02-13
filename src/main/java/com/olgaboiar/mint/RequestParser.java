package com.olgaboiar.mint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestParser {


    public Map<String, String> parseRequestHeaders(List<String> incomingRequest) {
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

    public Integer parsePort(Map<String, String> requestHeaders) {
        return Integer.parseInt(requestHeaders.get("Host").split(":")[1]);
    }

    public String parseHost(Map<String, String> requestHeaders) {
        return requestHeaders.get("Host").split(":")[0];
    }

    public String parseContentLength(Map<String, String> requestHeaders) {
        return requestHeaders.get("Content-Length").split(":")[0];
    }

    public Boolean contentLengthExist(Map<String, String> requestHeaders) {
        return (requestHeaders.get("Content-Length") != null);
    }

    public String parseProtocol(String[] requestLine) {
        return requestLine[2].split("/")[0].toLowerCase();
    }

    public String parseMethod(String[] requestLine) {
        return requestLine[0];
    }

    public String parsePath(String[] requestLine) {
        return requestLine[1];
    }
}
