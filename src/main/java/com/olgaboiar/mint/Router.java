package com.olgaboiar.mint;

import java.io.File;
import java.util.Arrays;

public class Router {
    ResponseGenerator responseGenerator = new ResponseGenerator();
    Response response;
    private String[] allowedRoutes = {"/simple_get"};

    public Response route(Request request) {
        String method = request.getMethod();
        System.out.println("method is " + request.getMethod());
        System.out.println("uri is " + request.getRequestedFile());
        System.out.println("protocol is " + request.getProtocol());
        String file = "." + request.getRequestedFile();
        System.out.println(method);
        System.out.println(file);
        File searchForFile = new File(file);
        if (searchForFile.isFile()) {
            System.out.println("file exists, append it to response body");
            response = responseGenerator.generateResponse();
        } else if (Arrays.asList(allowedRoutes).contains(request.getRequestedFile())) {
            System.out.println("route allowed");
            response = responseGenerator.generateResponse();
        }
        else {
            System.out.println("file doesn't exist, give not found error");
            response = responseGenerator.generateNotFoundResponse();
        }
        return response;
    }
}
