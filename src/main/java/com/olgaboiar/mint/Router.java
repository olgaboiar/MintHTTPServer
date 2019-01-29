package com.olgaboiar.mint;

import com.olgaboiar.mint.handlers.FileHandler;
import com.olgaboiar.mint.handlers.NotFoundHandler;
import com.olgaboiar.mint.handlers.RouteHandler;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Router {
    private String[] allowedRoutes = {"/simple_get", "/method_options", "/get_with_body"};

    public Response route(Request request) throws IOException {
        String file = "." + request.getRequestedFile();
        File searchForFile = new File(file);
        if (searchForFile.isFile()) {
            return new FileHandler().handleRequest(request);
        } else if (Arrays.asList(allowedRoutes).contains(request.getRequestedFile())) {
            return new RouteHandler().handleRequest(request);
        }
        else {
            return new NotFoundHandler().handleRequest(request);
        }
    }
}
