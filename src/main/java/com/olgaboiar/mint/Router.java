package com.olgaboiar.mint;

import java.io.File;
import java.util.Arrays;

public class Router {
    ResponseGenerator responseGenerator = new ResponseGenerator();
    Response response;
    private String[] allowedRoutes = {"/simple_get"};

    public Response route(Request request) {
        String method = request.getMethod();
        String file = "." + request.getRequestedFile();
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